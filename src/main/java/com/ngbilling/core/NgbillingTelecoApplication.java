package com.ngbilling.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NgbillingTelecoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NgbillingTelecoApplication.class, args);
    }

}
