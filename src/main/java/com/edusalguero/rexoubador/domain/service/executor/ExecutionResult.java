package com.edusalguero.rexoubador.domain.service.executor;

import java.util.HashMap;


public class ExecutionResult {

    private final HashMap<String, Object> hashMap;

    public ExecutionResult() {
        this.hashMap = new HashMap<String, Object>();
    }

    public void set(String key, Object value) {
        hashMap.put(key, value);
    }

    public HashMap<String, Object> get() {
        return hashMap;
    }

    public Object get(String key) {
        return hashMap.get(key);
    }

    public Boolean hasKey(String key)
    {
        return  hashMap.containsKey(key);
    }

    @Override
    public String toString() {
        return hashMap.toString();
    }
}
