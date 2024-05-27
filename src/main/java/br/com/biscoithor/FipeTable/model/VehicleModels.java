package br.com.biscoithor.FipeTable.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleModels (@JsonAlias("modelos") List<Data> models){
}
