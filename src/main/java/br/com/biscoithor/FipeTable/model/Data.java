package br.com.biscoithor.FipeTable.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Data(@JsonAlias("codigo") String code, @JsonAlias("nome") String name){
}
