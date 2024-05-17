package com.example.weatherreportlab3.service;


import com.example.weatherreportlab3.service.client.met.ClientMET;
import com.example.weatherreportlab3.service.client.meteo.ClientMETEO;
import com.example.weatherreportlab3.service.client.meteo.generated.ForecastMETEO;
import com.example.weatherreportlab3.service.client.smhi.ClientSMHI;
import com.example.weatherreportlab3.service.client.met.generated.ForecastMET;
import com.example.weatherreportlab3.service.model.Forecast;
import com.example.weatherreportlab3.service.client.smhi.generated.ForecastSMHI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForecastService {

    private final ClientSMHI clientSMHI;
    private final ClientMET clientMET;
    private final ClientMETEO clientMETEO;

    @Autowired
    public ForecastService(ClientSMHI clientSMHI, ClientMET clientMET, ClientMETEO clientMETEO) {
        this.clientSMHI = clientSMHI;
        this.clientMET = clientMET;
        this.clientMETEO = clientMETEO;
    }

    public Forecast getOptimizedForecast() {
        ForecastMET forecastMET = this.clientMET.getForecast();
        ForecastSMHI forecastSMHI = this.clientSMHI.getForecast();
        ForecastMETEO forecastMETEO = this.clientMETEO.getForecast();
        return Forecast.optimizedForecast(forecastMET, forecastSMHI, forecastMETEO);
    }
}
