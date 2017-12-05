package com.example.reactive;

import com.example.reactive.configuration.UsersSpringConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootApplicationStarter {
    public static void main(String[] args) throws Exception {
        System.setProperty("spring.jpa.hibernate.ddl-auto", "create-drop");
        SpringApplication.run(UsersSpringConfiguration.class, args);
    }
}

