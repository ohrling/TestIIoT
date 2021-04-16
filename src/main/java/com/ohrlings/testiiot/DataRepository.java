package com.ohrlings.testiiot;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class DataRepository {
    List<ValveData> data;

    public DataRepository() {
        this.data = List.of(
                new ValveData(
                        "valvemonitor01",
                        0,
                        26,
                        3,
                        40,
                        1247720,
                        new BigDecimal("0.60001")),
                new ValveData(
                        "valvemonitor02",
                        0,
                        20,
                        3,
                        40,
                        1247720,
                        new BigDecimal("0.60001")),
                new ValveData(
                        "valvemonitor03",
                        0,
                        10,
                        3,
                        40,
                        1247720,
                        new BigDecimal("0.60001")),
                new ValveData(
                        "valvemonitor04",
                        0,
                        32,
                        3,
                        40,
                        1247720,
                        new BigDecimal("0.60001")),
                new ValveData(
                        "valvemonitor05",
                        0,
                        37,
                        3,
                        40,
                        1247720,
                        new BigDecimal("0.60001")),
                new ValveData(
                        "valvemonitor06",
                        0,
                        41,
                        3,
                        40,
                        1247720,
                        new BigDecimal("0.60001")),
                new ValveData(
                        "valvemonitor07",
                        0,
                        44,
                        3,
                        40,
                         1247720,
                        new BigDecimal("0.60001")),
                new ValveData(
                        "valvemonitor08",
                        0,
                        45,
                        3,
                        40,
                        1247720,
                        new BigDecimal("0.60001")),
                new ValveData(
                        "valvemonitor09",
                        0,
                        52,
                        3,
                        40,
                        1247720,
                        new BigDecimal("0.60001")),
                new ValveData(
                        "valvemonitor10",
                        0,
                        54,
                        3,
                        40,
                        1247720,
                        new BigDecimal("0.60001"))
        );
    }

    public ValveData getValveData(int valve) {
        return data.get(valve);
    }
}
