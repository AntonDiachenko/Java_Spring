package com.jac.project.restservice;


import com.jac.project.exception.EmployeeExistException;
import com.jac.project.exception.EmployeeNotFoundException;
import com.jac.project.model.Employee;
import com.jac.project.repository.model.EmployeeBranch;
import com.jac.project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 45000)
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("employee")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }


    @GetMapping("employee/branchid/{branchID}")
    public ResponseEntity<List<EmployeeBranch>> getEmployeesByBranchID(@PathVariable int branchID){
        return new ResponseEntity<>(employeeService.getEmployeesByBranchID(branchID), HttpStatus.OK);
    }



    @GetMapping("employee/id/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

//    @GetMapping("employee/name/{lastName}")
//    public ResponseEntity<Customer> getCustomerByLastName(@PathVariable String lastName){
//        return new ResponseEntity<>(customerService.getCustomerByLastName(lastName), HttpStatus.OK);
//    }

    @DeleteMapping("employee/id/{id}")
    public ResponseEntity deleteEmployee(@PathVariable int id){
        try{
            employeeService.deleteEmployee(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        catch (EmployeeNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
    {
        try{
            return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
        }
        catch (EmployeeExistException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("employee/id/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee){
        try{
            return new ResponseEntity<>(employeeService.updateEmployee(id, employee), HttpStatus.OK);
        }
        catch (EmployeeNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
