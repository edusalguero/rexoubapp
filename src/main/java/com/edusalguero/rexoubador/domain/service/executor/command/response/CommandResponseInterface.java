package com.edusalguero.rexoubador.domain.service.executor.command.response;

import java.util.HashMap;

public interface CommandResponseInterface {
    Object getName();

    Object getData(String key);

    HashMap<String, Object> getData();

    String toJson();
}
