package com.tr.innova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.tr.innova"})
public class QuickstartServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickstartServerApplication.class, args);
    }

}
