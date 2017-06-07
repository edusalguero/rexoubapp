package com.edusalguero.rexoubapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class RexoubappApplication {


    public static void main(String[] args) {
        SpringApplication.run(RexoubappApplication.class, args);
    }
}
