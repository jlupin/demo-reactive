package com.example.reactive;

import com.example.reactive.model.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ReactiveClientApplication {
    @Bean
    public WebClient getWebClient() {
        return WebClient.create("http://localhost:20000");
    }

    @Bean
    public CommandLineRunner cmd(final WebClient webClient) {
        return args -> webClient.get()
                .uri("/reactive/person/list")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(Person.class))
                .doOnNext(System.out::println)
                .blockLast();
    }

    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(ReactiveClientApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }
}

