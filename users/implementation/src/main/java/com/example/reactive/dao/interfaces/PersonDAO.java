package com.example.reactive.dao.interfaces;

import com.example.reactive.dao.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonDAO extends CrudRepository<Person, Long> {
}
