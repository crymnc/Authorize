package com.experiment.authorize;

import com.experiment.authorize.util.Timer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthorizeApplication {
    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(AuthorizeApplication.class, args);
        Timer timer =new Timer();
        timer.log();
    }
}