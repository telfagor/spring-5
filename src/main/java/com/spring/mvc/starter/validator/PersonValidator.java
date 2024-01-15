package com.spring.mvc.starter.validator;

import com.spring.mvc.starter.model.Person;
import com.spring.mvc.starter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if (personService.findByEmail(person.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "The email is already taken");
        }
    }
}
