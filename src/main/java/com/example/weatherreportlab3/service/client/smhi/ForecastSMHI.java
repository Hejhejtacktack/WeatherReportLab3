
package com.example.weatherreportlab3.service.client.smhi;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.List;
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
    "approvedTime",
    "referenceTime",
    "geometry",
    "timeSeries"
})
public class ForecastSMHI {

    @JsonProperty("approvedTime")
    private String approvedTime;
    @JsonProperty("referenceTime")
    private String referenceTime;
    @JsonProperty("geometry")
    private Geometry geometry;
    @JsonProperty("timeSeries")
    private List<TimeSeries> timeSeries;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("approvedTime")
    public String getApprovedTime() {
        return approvedTime;
    }

    @JsonProperty("approvedTime")
    public void setApprovedTime(String approvedTime) {
        this.approvedTime = approvedTime;
    }

    @JsonProperty("referenceTime")
    public String getReferenceTime() {
        return referenceTime;
    }

    @JsonProperty("referenceTime")
    public void setReferenceTime(String referenceTime) {
        this.referenceTime = referenceTime;
    }

    @JsonProperty("geometry")
    public Geometry getGeometry() {
        return geometry;
    }

    @JsonProperty("geometry")
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @JsonProperty("timeSeries")
    public List<TimeSeries> getTimeSeries() {
        return timeSeries;
    }

    @JsonProperty("timeSeries")
    public void setTimeSeries(List<TimeSeries> timeSeries) {
        this.timeSeries = timeSeries;
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
        TimeSeries timeSeries = getNext24hTimeSeries();
        Parameter parameter = timeSeries.getTemperature();
        return parameter.getValues().get(0);
    }

    public Double getTomorrowsHumidity() {
        TimeSeries timeSeries = getNext24hTimeSeries();
        Parameter parameter = timeSeries.getHumidity();
        return parameter.getValues().get(0); // TODO FIX
    }

    private TimeSeries getNext24hTimeSeries() {
        return this.timeSeries
                .stream()
                .filter((timeSeries) -> ZonedDateTime.parse(timeSeries.getValidTime()).isAfter(ZonedDateTime.now().plusHours(24)))
                .findFirst().orElseThrow(() -> new NoSuchElementException("Couldn't find tomorrows TimeSeries"));
    }
}
