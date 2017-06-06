package com.edusalguero.rexoubapp.domain.service.executor.command;


import com.edusalguero.rexoubapp.domain.service.executor.command.response.CommandResponseInterface;

public interface CommandInterface {

    String getCommandString();

    CommandResponseInterface parseResult(String result);
}
