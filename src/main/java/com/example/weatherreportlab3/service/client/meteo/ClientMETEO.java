package com.example.weatherreportlab3.service.client.meteo;

import com.example.weatherreportlab3.service.client.meteo.generated.ForecastMETEO;
import org.springframework.web.service.annotation.GetExchange;

public interface ClientMETEO {

    @GetExchange("/forecast?latitude=59.3094&longitude=18.0234&hourly=temperature_2m,relative_humidity_2m&forecast_days=3")
    ForecastMETEO getForecast();
}
