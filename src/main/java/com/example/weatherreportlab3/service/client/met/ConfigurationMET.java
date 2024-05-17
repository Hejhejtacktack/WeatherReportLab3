package com.example.weatherreportlab3.service.client.met;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ConfigurationMET {

    private final static String BASE_URL_MET = "https://api.met.no/weatherapi";

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
}
