package com.spring.mvc.starter.service;

import com.spring.mvc.starter.dao.PersonDao;
import com.spring.mvc.starter.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class PersonService {

  private final PersonDao personDao;

    @Autowired
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public Person create(Person person) {
        return personDao.create(person);
    }

    public Boolean update(Person person) {
        return personDao.update(person);
    }

    public List<Person> findAll() {
        return personDao.findAll();
    }

    public Optional<Person> findById(Integer id) {
        return personDao.findById(id);
    }

    public Optional<Person> findByEmail(String email) {
        return personDao.findByEmail(email);
    }

    public Boolean deleteById(Integer id) {
        return personDao.delete(id);
    }
}
