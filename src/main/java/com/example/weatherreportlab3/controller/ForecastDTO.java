package com.example.weatherreportlab3.controller;

import com.example.weatherreportlab3.service.model.Forecast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record ForecastDTO(
        String origin,
        Double temperature,
        Double humidity,
        String timestamp) {

    public ForecastDTO(Forecast forecast) {
        this(forecast.getOrigin(),
                forecast.getTemperature(),
                forecast.getHumidity(),
                forecast.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
