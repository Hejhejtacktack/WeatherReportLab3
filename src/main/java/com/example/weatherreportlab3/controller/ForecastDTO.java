package com.example.weatherreportlab3.controller;

import com.example.weatherreportlab3.service.model.Forecast;

import java.time.LocalDateTime;

public class ForecastDTO {

    public final String origin;
    public final Double temperature;
    public final Double humidity;
    public final LocalDateTime timestamp;

    public ForecastDTO(Forecast forecast) {
        this.origin = forecast.origin;
        this.temperature = forecast.temperature;
        this.humidity = forecast.humidity;
        this.timestamp = forecast.timestamp;
    }
}
