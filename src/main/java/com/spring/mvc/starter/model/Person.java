package com.spring.mvc.starter.model;

import javax.validation.constraints.*;

public class Person {

    private Integer id;

    @NotBlank(message = "The name cannot be empty!")
    private String name;

    @NotEmpty(message = "The email cannot be empty!")
    @Email(message = "The email is invalid!")
    private String email;

    @Min(value = 10, message = "Age cannot be  less than 10 years!")
    @Max(value = 90, message = "Age cannot be greater than 90 years!")
    private Integer age;

    @Pattern(regexp = "^[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "The address must me in this format: Country, City, Postal Code")
    private String address;

    public Person() {

    }

    public Person(Integer id, String name, String email, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
