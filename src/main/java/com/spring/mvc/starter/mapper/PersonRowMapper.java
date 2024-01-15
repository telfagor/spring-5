package com.spring.mvc.starter.mapper;

import com.spring.mvc.starter.exception.DataRetrieveFailedException;
import com.spring.mvc.starter.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) {
        try {
            return new Person(
                    resultSet.getObject("id", Integer.class),
                    resultSet.getObject("person_name", String.class),
                    resultSet.getObject("email", String.class),
                    resultSet.getObject("person_age", Integer.class),
                    resultSet.getObject("address", String.class)
            );
        } catch (SQLException ex) {
            throw new DataRetrieveFailedException("Cannot retrieve data from the ResultSet!", ex);
        }
    }
}
