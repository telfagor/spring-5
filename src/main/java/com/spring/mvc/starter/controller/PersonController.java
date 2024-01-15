package com.spring.mvc.starter.controller;

import com.spring.mvc.starter.validator.PersonValidator;
import org.springframework.ui.Model;
import com.spring.mvc.starter.model.Person;
import com.spring.mvc.starter.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personService.findAll());
        return "people/index";
    }

    @GetMapping("/new")
    public String createNewPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/create-person";
    }

    /*@PostMapping
    public String create(@RequestParam(value = "name") String name,
                         @RequestParam(value = "email") String email,
                         @RequestParam(value = "age") int age,
                         Model model) {
        Person person = new Person();
        person.setName(name);
        person.setEmail(email);
        person.setAge(age);

        personService.create(person);
        model.addAttribute(person);

        return "redirect:/people";
    }*/

    //Prin @ModelAttribute
    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/create-person";
        }

        personService.create(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("person", personService.findById(id).orElse(null));
        return "people/edit-person";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") Integer id) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/edit-person";
        }

        personService.update(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("person", personService.findById(id).orElse(null));
        return "people/person";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        personService.deleteById(id);
        return "redirect:/people";
    }
}
