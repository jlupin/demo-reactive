package com.example.reactive.controller;

import com.example.reactive.dao.interfaces.PersonDAO;
import com.example.reactive.dao.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
public class InfoController {
    @Autowired
    private PersonDAO personDAO;

    @GetMapping("/info")
    public Map<String, String> getInfo() {
        Map<String, String> result = new HashMap<>();

        result.put("A", "A1");
        result.put("B", "B1");

        return result;
    }

    @PostMapping("/person/create")
    public Person createPerson(@RequestBody Person person) {
        return personDAO.save(person);
    }

    @GetMapping("/person/find/{id}")
    public Person findPerson(@PathVariable Long id) {
        return personDAO.findById(id).get();
    }

    @DeleteMapping("/person/delete/{id}")
    public Person deletePerson(@PathVariable Long id) {
        final Optional<Person> personOptional = personDAO.findById(id);
        if (personOptional.isPresent()) {
            personDAO.delete(personOptional.get());
        }
        return personOptional.get();
    }

    @GetMapping("/person/list")
    public List<Person> listPersons() {
        final List<Person> result = new ArrayList<>();
        for (final Person person : personDAO.findAll()) {
            result.add(person);
        }
        return result;
    }
}
