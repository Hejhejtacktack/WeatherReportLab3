package com.example.weatherreportlab3.controller;

import com.example.weatherreportlab3.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weather")
public class ForecastController {

    private final ForecastService forecastService;

    @Autowired
    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("/liljeholmen")
    public String one(Model model) {
        ForecastDTO forecastDTO = new ForecastDTO(this.forecastService.getOptimizedForecast());
        model.addAttribute("origin", forecastDTO.origin());
        model.addAttribute("timestamp", forecastDTO.timestamp());
        model.addAttribute("temperature", forecastDTO.temperature());
        model.addAttribute("humidity", forecastDTO.humidity());
        return "weather";
    }
}
