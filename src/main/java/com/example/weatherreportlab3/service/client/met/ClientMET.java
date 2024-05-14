package com.example.weatherreportlab3.service.client.met;

import org.springframework.web.service.annotation.GetExchange;

public interface ClientMET {

    @GetExchange("/locationforecast/2.0/compact?lat=59.3110&lon=18.0300")
    ForecastMET getForecast();
}
