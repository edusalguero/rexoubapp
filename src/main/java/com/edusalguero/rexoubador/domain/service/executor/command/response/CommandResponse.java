package com.edusalguero.rexoubador.domain.service.executor.command.response;

import com.google.gson.Gson;

import java.util.HashMap;

abstract public class CommandResponse implements CommandResponseInterface {
    protected final HashMap<String, Object> result = new HashMap<String, Object>();

    public void addData(String key, Object value) {
        getDataContent().put(key, value);
    }

    private HashMap<String, Object> getDataContent() {
        return (HashMap<String, Object>) result.get("data");
    }

    public String getType() {
        return (String) result.get("type");
    }

    @Override
    public Object getData(String key) {
        return getDataContent().get(key);
    }

    @Override
    public HashMap<String, Object> getData() {
        return getDataContent();
    }

    public Boolean hasKey(String key) {
        return getDataContent().containsKey(key);
    }

    @Override
    public String toString() {
        return result.toString();
    }

    public HashMap<String, Object> getResult() {
        return result;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(result);
    }
}
