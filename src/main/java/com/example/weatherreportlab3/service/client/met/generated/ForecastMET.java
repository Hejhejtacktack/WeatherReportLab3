
package com.example.weatherreportlab3.service.client.met.generated;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "geometry",
    "properties"
})
public class ForecastMET {

    @JsonProperty("type")
    private String type;
    @JsonProperty("geometry")
    private Geometry geometry;
    @JsonProperty("properties")
    private Properties properties;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("geometry")
    public Geometry getGeometry() {
        return geometry;
    }

    @JsonProperty("geometry")
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @JsonProperty("properties")
    public Properties getProperties() {
        return properties;
    }

    @JsonProperty("properties")
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Double getTomorrowsTemperature() {
        Timeseries timeseries = getNext24hTimeseries();
        return timeseries.getData().getInstant().getDetails().getAirTemperature();
    }

    public Double getTomorrowsHumidity() {
        Timeseries timeseries = getNext24hTimeseries();
        return timeseries.getData().getInstant().getDetails().getRelativeHumidity();
    }

    private Timeseries getNext24hTimeseries() {
        return this.properties.getTimeseries()
                .stream()
                .filter((timeStamp) -> ZonedDateTime.parse(timeStamp.getTime()).isAfter(ZonedDateTime.now().plusHours(24)))
                .findFirst().orElseThrow(() -> new NoSuchElementException("Couldn't find tomorrows Timeseries"));
    }
}
