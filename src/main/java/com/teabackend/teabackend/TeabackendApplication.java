package com.teabackend.teabackend;

import com.teabackend.teabackend.config.CROSConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class TeabackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeabackendApplication.class, args);
    }

}
