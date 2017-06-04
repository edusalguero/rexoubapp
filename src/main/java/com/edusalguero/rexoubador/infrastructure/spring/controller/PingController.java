package com.edusalguero.rexoubador.infrastructure.spring.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description="Ping operation")
public class PingController {

    @RequestMapping(method = RequestMethod.GET, path = "/v1/ping", produces = "application/json")
    public String ping() {
        return "pong";
    }

}
