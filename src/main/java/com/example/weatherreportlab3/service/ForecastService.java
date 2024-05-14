package com.example.weatherreportlab3.service;


import com.example.weatherreportlab3.service.client.met.ClientMET;
import com.example.weatherreportlab3.service.client.meteo.ClientMETEO;
import com.example.weatherreportlab3.service.client.meteo.ForecastMETEO;
import com.example.weatherreportlab3.service.client.smhi.ClientSMHI;
import com.example.weatherreportlab3.service.client.met.ForecastMET;
import com.example.weatherreportlab3.service.model.Forecast;
import com.example.weatherreportlab3.service.client.smhi.ForecastSMHI;
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

//    public static Forecast optimizedForecast(ForecastMET forecastMET,
//                                             ForecastSMHI forecastSMHI) {
//        Double temperatureMET = forecastMET.getTomorrowsTemperature();
//        Double temperatureSMHI = forecastSMHI.getTomorrowsTemperature();
//
//        Double humidityMET = forecastMET.getTomorrowsHumidity();
//        Double humiditySMHI = forecastSMHI.getTomorrowsHumidity();
//
//        double scoreMET = Math.abs(temperatureMET - IDEAL_TEMP) + Math.abs(humidityMET - IDEAL_HUM);
//        double scoreSMHI = Math.abs(temperatureSMHI - IDEAL_TEMP) + Math.abs(temperatureSMHI - IDEAL_HUM);
//
//        return (scoreMET < scoreSMHI) ?
//                new Forecast("MET", temperatureMET, humidityMET, LocalDateTime.now().plusHours(24)) :
//                new Forecast("SMHI", temperatureSMHI, humiditySMHI, LocalDateTime.now().plusHours(24));
//    }
}


//SMHI
//    public Double getTomorrowsTemperature() {
//        TimeSeriesDTO timeSeries = getNext24hTimeSeries();
//        ParameterDTO parameter = timeSeries.getCelsius();
//        return parameter.getValues().get(0);
//    }
//
//    public Double getTomorrowsHumidity() {
//        TimeSeriesDTO timeSeries = getNext24hTimeSeries();
//        ParameterDTO parameter = timeSeries.getCelsius();
//        return parameter.getValues().get(0);
//    }
//
//    private TimeSeriesDTO getNext24hTimeSeries() {
//        return this.timeSeries
//                .stream()
//                .filter((timeSeries) -> ZonedDateTime.parse(timeSeries.getValidTime()).isAfter(ZonedDateTime.now().plusHours(24)))
//                .findFirst().orElseThrow(() -> new NoSuchElementException("Couldn't find tomorrows TimeSeries"));
//    }
//
//    MET
//public Double getTomorrowsTemperature() {
//        TimeseriesDTO timeseries = getNext24hTimeseries();
//        return timeseries.getData().getInstant().getDetails().getAirTemperature();
//        }
//
//public Double getTomorrowsHumidity() {
//        TimeseriesDTO timeseries = getNext24hTimeseries();
//        return timeseries.getData().getInstant().getDetails().getRelativeHumidity();
//        }
//
//private TimeseriesDTO getNext24hTimeseries() {
//        return this.properties.getTimeseries()
//        .stream()
//        .filter((timeStamp) -> ZonedDateTime.parse(timeStamp.getTime()).isAfter(ZonedDateTime.now().plusHours(24)))
//        .findFirst().orElseThrow(() -> new NoSuchElementException("Couldn't find tomorrows Timeseries"));
//        }
