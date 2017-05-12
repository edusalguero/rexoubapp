package com.edusalguero.rexoubador.domain.service.executor.command;


import com.edusalguero.rexoubador.domain.service.executor.command.response.CommandResponse;

public interface CommandInterface {

    String getCommandString();

    CommandResponse parseResult(String result);
}
