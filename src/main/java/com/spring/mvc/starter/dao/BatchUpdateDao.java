package com.spring.mvc.starter.dao;

import com.spring.mvc.starter.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BatchUpdateDao {

    private static final String INSERT_SQL =
            "INSERT INTO person (person_name, email, person_age, address)" +
            "VALUES(?, ?, ?, ?)";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BatchUpdateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void simpleUpdate() {
        List<Person> people = create100People();

        for (Person person : people) {
            jdbcTemplate.update(INSERT_SQL,
                    person.getName(),
                    person.getEmail(),
                    person.getAge(),
                    person.getAddress());
        }
    }

    public void testBatchUpdate() {
        List<Person> people = create100People();
        jdbcTemplate.batchUpdate(INSERT_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, people.get(i).getName());
                preparedStatement.setString(2, people.get(i).getEmail());
                preparedStatement.setInt(3, people.get(i).getAge());
                preparedStatement.setString(4, people.get(1).getAddress());
            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });
    }

    private List<Person> create100People() {
        List<Person> people = new ArrayList<>(1000);

        for (int i = 0; i < 1000; i++) {
            Person person = new Person();
            person.setName("Name" + i);
            person.setEmail("email" + i);
            person.setAge(i);
            person.setAddress("some address");
            people.add(person);
        }

        return people;
    }
}
