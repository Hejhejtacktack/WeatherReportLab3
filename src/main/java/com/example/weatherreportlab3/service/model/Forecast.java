package com.example.weatherreportlab3.service.model;

import com.example.weatherreportlab3.service.client.met.ForecastMET;
import com.example.weatherreportlab3.service.client.meteo.ForecastMETEO;
import com.example.weatherreportlab3.service.client.smhi.ForecastSMHI;

import java.time.LocalDateTime;

public class Forecast {
    private final static Double IDEAL_TEMP = 30.0;
    private final static Double IDEAL_HUM = 40.0;
    public final String origin;
    public final Double temperature;
    public final Double humidity;
    public final LocalDateTime timestamp;

    private Forecast(String origin, Double temperature, Double humidity, LocalDateTime timestamp) {
        this.origin = origin;
        this.temperature = temperature;
        this.humidity = humidity;
        this.timestamp = timestamp;
    }

    public static Forecast optimizedForecast(ForecastMET forecastMET,
                                             ForecastSMHI forecastSMHI,
                                             ForecastMETEO forecastMETEO) {
        Double temperatureMET = forecastMET.getTomorrowsTemperature();
        Double temperatureSMHI = forecastSMHI.getTomorrowsTemperature();
        Double temperatureMETEO = forecastMETEO.getTomorrowsTemperature();

        Double humidityMET = forecastMET.getTomorrowsHumidity();
        Double humiditySMHI = forecastSMHI.getTomorrowsHumidity();
        Double humidityMETEO = forecastMETEO.getTomorrowsHumidity();

        double scoreMET = Math.abs(temperatureMET - IDEAL_TEMP) + Math.abs(humidityMET - IDEAL_HUM);
        double scoreSMHI = Math.abs(temperatureSMHI - IDEAL_TEMP) + Math.abs(humiditySMHI - IDEAL_HUM);
        double scoreMETEO = Math.abs(temperatureMETEO - IDEAL_TEMP) + Math.abs(humidityMETEO - IDEAL_HUM);

        if (scoreMET < scoreSMHI && scoreMET < scoreMETEO) {
            return new Forecast("MET", temperatureMET, humidityMET, LocalDateTime.now().plusHours(24));
        } else if (scoreSMHI < scoreMETEO) {
            return new Forecast("SMHI", temperatureSMHI, humiditySMHI, LocalDateTime.now().plusHours(24));
        } else {
            return new Forecast("METEO", temperatureMETEO, humidityMETEO, LocalDateTime.now().plusHours(24));
        }
    }
}