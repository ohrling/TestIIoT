package com.ohrlings.testiiot;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ValveData {
    private final String name;
    private long count;
    private final int position;
    private int pressureDp;
    private int temperaturePosition;
    private long travelAccumulator;
    private BigDecimal vibrationActuator;

    public ValveData(
            String name,
            long count,
            int position,
            int pressureDp,
            int temperaturePosition,
            long travelAccumulator,
            BigDecimal vibrationActuator) {
        this.name = name;
        this.count = count;
        this.position = position;
        this.pressureDp = pressureDp;
        this.temperaturePosition = temperaturePosition;
        this.travelAccumulator = travelAccumulator;
        this.vibrationActuator = vibrationActuator.setScale(2, RoundingMode.HALF_UP);
    }

    public String getName() {
        return name;
    }

    public long getCount() {
        return count;
    }

    public int getPosition() {
        return position;
    }

    public int getPressureDp() {
        return pressureDp;
    }

    public int getTemperaturePosition() {
        return temperaturePosition;
    }

    public long getTravelAccumulator() {
        return travelAccumulator;
    }

    public BigDecimal getVibrationActuator() {
        return vibrationActuator;
    }

    public void setCount() {
        this.count = this.count + 1;
    }

    public void setPressureDp(int pressureDp) {
        this.pressureDp = pressureDp;
    }

    public void setTemperaturePosition(int temperaturePosition) {
        this.temperaturePosition = temperaturePosition;
    }

    public void setTravelAccumulator() {
        this.travelAccumulator = this.travelAccumulator + 1;
    }

    public void setVibrationActuator() {
        BigDecimal temp = new BigDecimal(String.valueOf(Math.sin(this.vibrationActuator.doubleValue())));
        this.vibrationActuator = temp.setScale(2, RoundingMode.HALF_UP);
    }
}
