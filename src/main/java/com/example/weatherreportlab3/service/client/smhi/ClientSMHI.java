package com.example.weatherreportlab3.service.client.smhi;

import org.springframework.web.service.annotation.GetExchange;

public interface ClientSMHI {

    @GetExchange("/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json")
    ForecastSMHI getForecast();
}
