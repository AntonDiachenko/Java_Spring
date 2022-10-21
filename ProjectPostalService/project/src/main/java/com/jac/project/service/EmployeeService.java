package com.jac.project.service;


import com.jac.project.exception.*;
import com.jac.project.model.Employee;
import com.jac.project.repository.EmployeeRepository;
import com.jac.project.repository.model.EmployeeBranch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    ArrayList<Employee> employees = new ArrayList<>();

    @Autowired
    private EmployeeRepository employeeRepository;

        public List<Employee> getEmployees(){
        return employeeRepository.getAllEmployees();
    }


        public List<EmployeeBranch> getEmployeesByBranchID(int id){
        return employeeRepository.getEmployeesByBranchID(id);
    }


    public Employee getEmployeeByLastName(String lastName) {
        try {
            return employeeRepository.getEmployeeByLastName(lastName);
        } catch (DatabaseException exc) {
            return null;
        }
    }

    public Employee getEmployeeById(int id) {
        try {
            return employeeRepository.getEmployeeById(id);
        } catch (DatabaseException exc) {
            return null;
        }
    }

    public void deleteEmployee(int id) {
        Employee fetchedEmployee = getEmployeeById(id);
        if (fetchedEmployee == null) {
            throw new EmployeeNotFoundException("the employee does not exist " + id);
        }
        employeeRepository.deleteEmployeeById(id);
    }

    public Employee saveEmployee(Employee employee) {
        if (getEmployeeByLastName(employee.getLastName()) == null) {
            return employeeRepository.saveEmployee(employee);
        } else {
            throw new EmployeeExistException("the employee with the same name exists");
        }
    }

    public Employee updateEmployee(int id, Employee employee) {
        Employee fetchedEmployee = getEmployeeById(id);
        if (fetchedEmployee == null) {
            throw new EmployeeNotFoundException("the employee does not exist " + id);
        }
        employeeRepository.updateEmployee(id, employee);

        return employee;
    }

// TO DELETE!!! GIVES ONLY 1 OBJECT of EMPLOYEE (NOT A LIST)
//    public Employee getEmployeeByBranch(int branchId){
//        try {
//            return employeeRepository.getEmployeeByBranch(branchId);
//        } catch (DatabaseException exc) {
//            throw new EmployeeNotFoundException("Employee is not available");
//        }
//    }

}
