package com.jac.project.service;

import com.jac.project.exception.CustomerExistException;
import com.jac.project.exception.CustomerNotFoundException;
import com.jac.project.exception.DatabaseException;
import com.jac.project.model.Customer;
import com.jac.project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    ArrayList<Customer> customers = new ArrayList<>();

    @Autowired
    CustomerRepository customerRepository;

    public CustomerService() {
        customers.add(new Customer(1,"John", "Smith1"));
        customers.add(new Customer(2, "John", "Smith2"));
        customers.add(new Customer(3, "John", "Smith3"));
    }

    public List<Customer> getCustomers(){
        return customerRepository.getAllCustomers();
    }

    public Customer getCustomerByLastName(String lastName) {
        try {
            return customerRepository.getCustomerByLastName(lastName);
        } catch (DatabaseException exc) {
            return null;
        }
    }

    public Customer getCustomerById(int id) {
        try {
            return customerRepository.getCustomerById(id);
        } catch (DatabaseException exc) {
            return null;
        }
    }

    public void deleteCustomer(int id) {
        Customer fetchedCustomer = getCustomerById(id);
        if (fetchedCustomer == null) {
            throw new CustomerNotFoundException("the customer does not exist " + id);
        }
        customerRepository.deleteCustomerById(id);
    }

    public Customer saveCustomer(Customer customer) {
        if (getCustomerByLastName(customer.getLastName()) == null) {
            return customerRepository.saveCustomer(customer);
        } else {
            throw new CustomerExistException("the customer with the same name exists");
        }
    }

    public Customer updateCustomer(int id, Customer customer) {
        Customer fetchedCustomer = getCustomerById(id);
        if (fetchedCustomer == null) {
            throw new CustomerNotFoundException("the customer does not exist " + id);
        }
        customerRepository.updateCustomer(id, customer);

        return customer;
    }


}
