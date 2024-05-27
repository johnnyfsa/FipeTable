package br.com.biscoithor.FipeTable.service;

import java.util.List;

public interface IDataConversion {
    <T> T getData(String json, Class<T> classType);

    <T> List<T> getList(String json, Class<T> classType);
}
