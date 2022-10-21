package com.jac.project.repository;

import com.jac.project.exception.DatabaseException;
import com.jac.project.model.Customer;
import com.jac.project.repository.model.CustomerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Customer> getAllCustomers(){
        try {
            return jdbcTemplate.query("SELECT CustID, FirstName, LastName from customers",
                    (rs, rowNum) -> new Customer(rs.getInt("CustID"),
                            rs.getString("FirstName"),
                            rs.getString("LastName")));
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getAllCustomers");
        }
    }

    public Customer getCustomerById(int id) {
        try {
            String sql = "SELECT * FROM customers WHERE CustID = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new CustomerRowMapper());

        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getCustomerById " + id);
        }
    }

    public Customer getCustomerByLastName(String lastName) {
        try {
            String sql = "SELECT * FROM customers WHERE LastName = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{lastName}, new CustomerRowMapper());

        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getCustomerByLastName " + lastName);
        }
    }

    public void deleteCustomerById(int id){
        try {
            jdbcTemplate.update("delete from customers where CustID = ?", id);
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in deleteCustomerById " + id);
        }
    }

    public Customer saveCustomer(Customer customer){
        try {
            jdbcTemplate.update("Insert into customers (FirstName, LastName) values (?,?)",
                    customer.getFirstName(), customer.getLastName());
            int id = jdbcTemplate.queryForObject(
                    "select max(CustID) from customers", Integer.class);
            customer.setCustID(id);
            return customer;
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in saveCustomer ");
        }
    }

    public void updateCustomer(int id, Customer customer){
        try {
            jdbcTemplate.update("UPDATE customers set FirstName=?, LastName=? WHERE CustID=?",
                    customer.getFirstName(), customer.getLastName(), id);
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in update " + id);
        }
    }

}
