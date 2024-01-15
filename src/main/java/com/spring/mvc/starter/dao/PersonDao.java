package com.spring.mvc.starter.dao;

import com.spring.mvc.starter.mapper.PersonRowMapper;
import com.spring.mvc.starter.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.sql.PreparedStatement;

@Component
public class PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String INSERT_SQL =
            "INSERT INTO person (person_name, email, person_age, address)" +
            "VALUES(?, ?, ?, ?)";

    private static final String UPDATE_SQL =
            "UPDATE person " +
            "SET person_name = ?," +
            "    email = ?," +
            "    person_age = ?," +
            "    address = ? " +
            "WHERE id = ?";


    private static final String FIND_ALL_SQL =
            "SELECT id," +
            "person_name," +
            "email," +
            "person_age," +
            "address " +
            "FROM person";

    private static final String DELETE_BY_ID_SQL =
            "DELETE FROM person WHERE id = ?";
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + " WHERE id = ?";

    private static final String FIND_BY_EMAIL_SQL = FIND_ALL_SQL + " WHERE email = ?";

    public Person create(Person person) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, new String[] {"id"});
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getEmail());
            preparedStatement.setInt(3, person.getAge());
            preparedStatement.setString(4, person.getAddress());
            return preparedStatement;
        }, keyHolder);

        person.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return person;
    }

    public Boolean update(Person person) {
        return jdbcTemplate.update(UPDATE_SQL,
                person.getName(),
                person.getEmail(),
                person.getAge(),
                person.getAddress(),
                person.getId()) > 0;
    }

    public Optional<Person> findById(Integer id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID_SQL, new PersonRowMapper(), id));
    }

    public Optional<Person> findByEmail(String email) {
        return jdbcTemplate.query(FIND_BY_EMAIL_SQL, new Object[]{email}, new PersonRowMapper()).stream()
                .findFirst();
    }

    /*public Optional<Person> findByEmail(String email) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_EMAIL_SQL, new PersonRowMapper(), email));
    }*/

    public List<Person> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL + " ORDER BY id", new PersonRowMapper());
    }

    public Boolean delete(Integer id) {
        return jdbcTemplate.update(DELETE_BY_ID_SQL, id) > 0;
    }
}
