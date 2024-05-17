package com.example.weatherreportlab3.service.client.meteo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ConfigurationMETEO {

    private final static String BASE_URL_METEO = "https://api.open-meteo.com/v1";

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
