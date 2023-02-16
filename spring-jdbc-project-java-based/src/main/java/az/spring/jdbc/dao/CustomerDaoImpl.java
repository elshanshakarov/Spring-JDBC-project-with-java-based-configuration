package az.spring.jdbc.dao;

import az.spring.jdbc.mapper.CustomerMapper;
import az.spring.jdbc.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerDaoImpl implements CustomerDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> getCustomerList() throws Exception {
        String query = "select * from customer where active=true";
        List customerList = jdbcTemplate.query(query, new CustomerMapper());
        /*
          CustomerMapper class-i yaratmadanda spring-in BeanPropertyRoWMapper class-dan da istifadə edə bilərik
          List customerList = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Customer.class));
         */
        return customerList;
    }

    @Override
    public Customer getCustomerById(Long customerId) throws Exception {
        String query = "select * from customer where active=true and id=?";
        Customer customer = jdbcTemplate.queryForObject(query, new CustomerMapper(), customerId);
        /*
          CustomerMapper class-i yaratmadanda spring-in BeanPropertyRoWMapper class-dan da istifadə edə bilərik
          Customer customer = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Customer.class), customerId);
         */
        return customer;
    }

    @Override
    public void addCustomer(Customer customer) throws Exception {
        String sql = "insert into customer(id, name,surname,phone,address,email,password)\n"
                + "values (nextval('customer_seq'),?,?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{customer.getName(), customer.getSurname(), customer.getPhone(), customer.getAddress(), customer.getEmail(), customer.getPassword()});
    }

    @Override
    public void updateCustomer(Customer customer) throws Exception {
        String query = "update customer set name=?, surname=?, phone=?, address=?, email=?, password=? where id=?";
        jdbcTemplate.update(query, new Object[]{customer.getName(), customer.getSurname(), customer.getPhone(), customer.getAddress(), customer.getEmail(), customer.getPassword(), customer.getId()});
    }

    @Override
    public void deleteCustomer(Long customerId) throws Exception {
        String query = "update customer set active=false where id=?";
        jdbcTemplate.update(query, new Object[]{customerId});
    }

    @Override
    public Long customerCount() throws Exception {
        String query = "select count(*) from customer where active=true";
        Long count = jdbcTemplate.queryForObject(query, Long.class);
        return count;
    }
}
