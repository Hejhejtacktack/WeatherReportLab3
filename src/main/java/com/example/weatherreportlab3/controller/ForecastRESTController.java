package com.example.weatherreportlab3.controller;

import com.example.weatherreportlab3.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ForecastRESTController {

    private final ForecastService forecastService;

    @Autowired
    public ForecastRESTController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("/liljeholmen")
    public ResponseEntity<ForecastDTO> getForecast() {
        return new ResponseEntity<>(new ForecastDTO(this.forecastService.getOptimizedForecast()), HttpStatus.OK);
    }
}
