
package com.example.weatherreportlab3.service.client.meteo;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "latitude",
    "longitude",
    "generationtime_ms",
    "utc_offset_seconds",
    "timezone",
    "timezone_abbreviation",
    "elevation",
    "hourly_units",
    "hourly"
})
public class ForecastMETEO {

    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("generationtime_ms")
    private Double generationtimeMs;
    @JsonProperty("utc_offset_seconds")
    private Integer utcOffsetSeconds;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("timezone_abbreviation")
    private String timezoneAbbreviation;
    @JsonProperty("elevation")
    private Integer elevation;
    @JsonProperty("hourly_units")
    private HourlyUnits hourlyUnits;
    @JsonProperty("hourly")
    private Hourly hourly;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("latitude")
    public Double getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("longitude")
    public Double getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("generationtime_ms")
    public Double getGenerationtimeMs() {
        return generationtimeMs;
    }

    @JsonProperty("generationtime_ms")
    public void setGenerationtimeMs(Double generationtimeMs) {
        this.generationtimeMs = generationtimeMs;
    }

    @JsonProperty("utc_offset_seconds")
    public Integer getUtcOffsetSeconds() {
        return utcOffsetSeconds;
    }

    @JsonProperty("utc_offset_seconds")
    public void setUtcOffsetSeconds(Integer utcOffsetSeconds) {
        this.utcOffsetSeconds = utcOffsetSeconds;
    }

    @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @JsonProperty("timezone_abbreviation")
    public String getTimezoneAbbreviation() {
        return timezoneAbbreviation;
    }

    @JsonProperty("timezone_abbreviation")
    public void setTimezoneAbbreviation(String timezoneAbbreviation) {
        this.timezoneAbbreviation = timezoneAbbreviation;
    }

    @JsonProperty("elevation")
    public Integer getElevation() {
        return elevation;
    }

    @JsonProperty("elevation")
    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    @JsonProperty("hourly_units")
    public HourlyUnits getHourlyUnits() {
        return hourlyUnits;
    }

    @JsonProperty("hourly_units")
    public void setHourlyUnits(HourlyUnits hourlyUnits) {
        this.hourlyUnits = hourlyUnits;
    }

    @JsonProperty("hourly")
    public Hourly getHourly() {
        return hourly;
    }

    @JsonProperty("hourly")
    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
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
        return this.hourly.getTemperature2m().get(getHourlyIndex());
    }

    public Double getTomorrowsHumidity() {
        return this.hourly.getRelativeHumidity2m().get(getHourlyIndex());
    }

    private Integer getHourlyIndex() {
        return IntStream.range(0, this.hourly.getTime().size())
                .filter((index) -> LocalDateTime.parse(this.hourly.getTime().get(index)).isAfter(LocalDateTime.now().plusHours(24)))
                .findFirst().orElseThrow(() -> new NoSuchElementException("Couldn't find Hourly's index"));
    }
}
