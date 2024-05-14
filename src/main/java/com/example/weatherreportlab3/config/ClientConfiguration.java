package com.example.weatherreportlab3.config;

import com.example.weatherreportlab3.service.client.met.ClientMET;
import com.example.weatherreportlab3.service.client.meteo.ClientMETEO;
import com.example.weatherreportlab3.service.client.smhi.ClientSMHI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfiguration {

    private final static String BASE_URL_SMHI = "https://opendata-download-metfcst.smhi.se/api";
    private final static String URI_SMHI ="/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json";
    private final static String BASE_URL_MET = "https://api.met.no/weatherapi";
    private final static String URI_MET = "/locationforecast/2.0/compact?lat=59.3110&lon=18.0300";
    private final static String BASE_URL_METEO = "https://api.open-meteo.com/v1";
    private final static String URI_METEO = "/forecast?latitude=59.3094&longitude=18.0234&hourly=temperature_2m,relative_humidity_2m&forecast_days=3";


    @Bean
    ClientSMHI clientSMHI() {
        WebClient client = WebClient
                .builder()
                .baseUrl(BASE_URL_SMHI)
                .build();
        WebClientAdapter adapter = WebClientAdapter
                .create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(adapter)
                .build();
        return factory.createClient(ClientSMHI.class);
    }

    @Bean
    ClientMET clientMET() {
        WebClient client = WebClient
                .builder()
                .baseUrl(BASE_URL_MET)
                .build();
        WebClientAdapter adapter = WebClientAdapter
                .create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(adapter)
                .build();
        return factory.createClient(ClientMET.class);
    }

    @Bean
    ClientMETEO clientMETEO() {
        WebClient client = WebClient
                .builder()
                .baseUrl(BASE_URL_METEO)
                .build();
        WebClientAdapter adapter = WebClientAdapter
                .create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(adapter)
                .build();
        return factory.createClient(ClientMETEO.class);
    }
}
