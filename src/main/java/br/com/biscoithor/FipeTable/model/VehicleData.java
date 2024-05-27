package br.com.biscoithor.FipeTable.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;   

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleData
        (
                @JsonAlias ("Valor") String valor,
                @JsonAlias ("Marca") String marca,
                @JsonAlias ("Modelo") String modelo,
                @JsonAlias ("AnoModelo") String anoModelo,
                @JsonAlias ("Combustivel") String tipoCombustivel,
                @JsonAlias ("CodigoFipe") String condigoFipe,
                @JsonAlias ("MesReferencia") String mesReferencia
        )
{
}
