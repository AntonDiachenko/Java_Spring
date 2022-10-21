package com.jac.project.restservice;

import com.jac.project.exception.CustomerExistException;
import com.jac.project.exception.CustomerNotFoundException;
import com.jac.project.model.Customer;
import com.jac.project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 45000)
@RequestMapping("/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("customer")
    public ResponseEntity<List<Customer>> getAll(){
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("customer/id/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id){
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @GetMapping("customer/name/{lastName}")
    public ResponseEntity<Customer> getCustomerByLastName(@PathVariable String lastName){
        return new ResponseEntity<>(customerService.getCustomerByLastName(lastName), HttpStatus.OK);
    }

    @DeleteMapping("customer/id/{id}")
    public ResponseEntity deleteCustomer(@PathVariable int id){
        try{
            customerService.deleteCustomer(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        catch (CustomerNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer)
    {
        try{
            return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.CREATED);
        }
        catch (CustomerExistException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("customer/id/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customer){
        try{
            return new ResponseEntity<>(customerService.updateCustomer(id, customer), HttpStatus.OK);
        }
        catch (CustomerNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
