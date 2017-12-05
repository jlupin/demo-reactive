package com.example.reactive.controller;

import com.example.reactive.dao.interfaces.PersonDAO;
import com.example.reactive.dao.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/reactive")
public class ReactiveInfoController {
    @Autowired
    private PersonDAO personDAO;

    @GetMapping("/info")
    public Mono<Map<String, String>> getInfo() {
        Map<String, String> result = new HashMap<>();

        result.put("A", "A1");
        result.put("B", "B1");

        return Mono.fromCallable(() -> result);
    }

    @PostMapping("/person/create")
    public Mono<Person> createPerson(@RequestBody Person person) {
        return Mono.justOrEmpty(personDAO.save(person));
    }

    @GetMapping("/person/find/{id}")
    public Mono<Person> findPerson(@PathVariable Long id) {
        return Mono.justOrEmpty(personDAO.findById(id));
    }

    @DeleteMapping("/person/delete/{id}")
    public Mono<Person> deletePerson(@PathVariable Long id) {
        final Optional<Person> personOptional = personDAO.findById(id);
        if (personOptional.isPresent()) {
            personDAO.delete(personOptional.get());
        }
        return Mono.justOrEmpty(personOptional.get());
    }

    @GetMapping("/person/list")
    public Flux<Person> listPersons() {
        return Flux.fromIterable(personDAO.findAll()).delayElements(Duration.ofSeconds(3));
    }
}
