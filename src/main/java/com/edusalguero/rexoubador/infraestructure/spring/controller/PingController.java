package com.edusalguero.rexoubador.infraestructure.spring.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @RequestMapping(method = RequestMethod.GET, path = "/ping", produces = "application/json")
    public String ping() {
        return "pong";
    }

}
