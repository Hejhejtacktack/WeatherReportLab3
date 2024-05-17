
package com.example.weatherreportlab3.service.client.smhi.generated;

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
    "validTime",
    "parameters"
})
public class TimeSeries {

    @JsonProperty("validTime")
    private String validTime;
    @JsonProperty("parameters")
    private List<Parameter> parameters;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("validTime")
    public String getValidTime() {
        return validTime;
    }

    @JsonProperty("validTime")
    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    @JsonProperty("parameters")
    public List<Parameter> getParameters() {
        return parameters;
    }

    @JsonProperty("parameters")
    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Parameter getTemperature() {
        return this.parameters
                .stream()
                .filter(parameter -> parameter.getName().equals("t"))
                .findFirst().orElseThrow(() -> new NoSuchElementException("Couldn't find Parameter with temperature"));
    }

    public Parameter getHumidity() {
        return this.parameters
                .stream()
                .filter(parameter -> parameter.getName().equals("r"))
                .findFirst().orElseThrow(() -> new NoSuchElementException("Couldn't find Parameter with humidity"));
    }
}
