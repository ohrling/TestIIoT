package com.ohrlings.testiiot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.*;

@Component
public class DataSender {
    private static final Logger log = LoggerFactory.getLogger(DataSender.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final Random random = new Random();
    private List<Map<String, Object>> data;

    private final DataRepository repository;

    public DataSender(RestTemplate restTemplate, ObjectMapper mapper, DataRepository repository) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        data = Collections.emptyList();
        this.repository = repository;
    }

    @Scheduled(fixedRate = 1000)
    public void sendData() {
        setData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
        try {
            String dataJSON = String.valueOf(mapper.writeValueAsString(data));
            log.info("Sending data.\n{}",dataJSON);
            HttpEntity<String> entity = new HttpEntity<>(dataJSON, headers);
            String response = restTemplate.exchange("http://localhost:8080/adapter", HttpMethod.POST, entity, String.class).getBody();
            log.info("Data sent. Response:\n{}", response);
        } catch (Exception e) {
            log.error("Couldn't parse data to JSON. {}", e.getMessage());
        }
    }

    private void setData() {
        int valveNr = random.nextInt(9);
        data = List.of(
                Map.of("bn", repository.getValveData(valveNr).getName(), "bt", Instant.now().toEpochMilli(), "bver", 10),
                Map.of("n", "control_deviation", "v", -0.5, "u", "s"),
                Map.of("n", "cycle_counter", "v", repository.getValveData(valveNr).getCount(), "u", "count"),
                Map.of("n", "position", "v", repository.getValveData(valveNr).getPosition(), "u", "m"),
                Map.of("n", "pressure_close", "v", random.nextInt(2), "u", "Deg"),
                Map.of("n", "pressure_dp", "v", repository.getValveData(valveNr).getPressureDp(), "u", "m3/s"),
                Map.of("n", "pressure_open", "v", random.nextInt(2), "u", "Deg"),
                Map.of("n", "set_point", "v", repository.getValveData(valveNr).getPosition(), "u", "m"),
                Map.of("n", "supply_pressure", "v", 5, "u", "bar"),
                Map.of("n", "temperature_position", "v", repository.getValveData(valveNr).getTemperaturePosition(), "u", "Cel"),
                Map.of("n", "travel_accumulator", "v", repository.getValveData(valveNr).getTravelAccumulator(), "u", "count"),
                Map.of("n", "vibration_actuator", "v", repository.getValveData(valveNr).getVibrationActuator(), "u", "bar")
        );
        repository.getValveData(valveNr).setCount();
        repository.getValveData(valveNr).setPressureDp(random.nextInt(5));
        repository.getValveData(valveNr).setTemperaturePosition(random.nextInt(46));
        repository.getValveData(valveNr).setTravelAccumulator();
        repository.getValveData(valveNr).setVibrationActuator();
    }
}
