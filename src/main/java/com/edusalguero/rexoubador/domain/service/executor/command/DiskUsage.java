package com.edusalguero.rexoubador.domain.service.executor.command;

import com.edusalguero.rexoubador.domain.service.executor.CommandInterface;
import com.edusalguero.rexoubador.domain.service.executor.ExecutionResult;

import java.util.HashMap;

public class DiskUsage implements CommandInterface {

    @Override
    public String getCommandString() {
        return "df -mlT | grep '^\\/dev'";
    }

    @Override
    public ExecutionResult parseResult(String result) {
        String[] mountPoints = result.split("\\n");

        ExecutionResult executionResult = new ExecutionResult();
        executionResult.set("type", "disk_usage");

        for (String point : mountPoints) {
            String[] parts = point.split("\\s+");
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("filesystem", parts[0]);
            map.put("total", parts[2]);
            map.put("used", parts[3]);
            map.put("free", parts[4]);
            map.put("percentage_of_use", parts[5].replace("%", ""));
            map.put("type", parts[1]);
            map.put("mounted_on", parts[6]);
            executionResult.set(parts[6], map);
        }

        return executionResult;
    }


}
