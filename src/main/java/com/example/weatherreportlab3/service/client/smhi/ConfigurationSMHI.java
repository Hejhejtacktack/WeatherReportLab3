package com.example.weatherreportlab3.service.client.smhi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ConfigurationSMHI {

    private final static String BASE_URL_SMHI = "https://opendata-download-metfcst.smhi.se/api";

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
}
