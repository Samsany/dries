package com.dries.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZSY
 */
@SpringBootApplication(scanBasePackages = "com.dries")
public class DriesAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriesAdminApplication.class, args);
    }

}
