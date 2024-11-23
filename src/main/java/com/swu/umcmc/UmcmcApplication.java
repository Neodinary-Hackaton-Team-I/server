package com.swu.umcmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UmcmcApplication {

    public static void main(String[] args) {
        SpringApplication.run(UmcmcApplication.class, args);
    }

}
