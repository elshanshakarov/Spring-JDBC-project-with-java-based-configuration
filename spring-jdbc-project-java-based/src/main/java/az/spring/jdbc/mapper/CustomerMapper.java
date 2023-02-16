package az.spring.jdbc.mapper;

import az.spring.jdbc.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<Customer> {
    // Bu class vasitəsilə resultSet-də gələn dəyərləri Customer-ə map edirik
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getLong("id"));
        customer.setName(rs.getString("name"));
        customer.setSurname(rs.getString("surname"));
        customer.setPhone(rs.getInt("phone"));
        customer.setAddress(rs.getString("address"));
        customer.setEmail(rs.getString("email"));
        customer.setPassword(rs.getString("password"));
        return customer;
    }
}
