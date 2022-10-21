package com.jac.project.repository;

import com.jac.project.exception.DatabaseException;
import com.jac.project.model.Employee;
import com.jac.project.repository.model.EmployeeBranch;
import com.jac.project.repository.model.EmployeeBranchRowMapper;
import com.jac.project.repository.model.EmployeeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<Employee> getAllEmployees(){
        try {
            return jdbcTemplate.query("SELECT EmpID, BranchID, FirstName, LastName from employees",
                    (rs, rowNum) -> new Employee(rs.getInt("EmpID"),
                            rs.getInt("BranchID"),
                            rs.getString("FirstName"),
                            rs.getString("LastName")));
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getAllEmployees");
        }
    }


        public List<EmployeeBranch> getEmployeesByBranchID(int id){

        try {
        String sql = "SELECT employees.EmpID employeeID, employees.FirstName employeeFirstName, employees.LastName employeeLastName," +
                "branches.BranchID branchID, branches.BranchName branchName " +
                "from employees, branches WHERE employees.BranchID=branches.BranchID and branches.BranchID =" + id +";";

        List<EmployeeBranch> employeeBranches = jdbcTemplate.query(
                sql,
                new EmployeeBranchRowMapper());
        return employeeBranches;
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getEmployeesByBranchID");
        }
    }


    public Employee getEmployeeById(int id) {
        try {
            String sql = "SELECT * FROM employees WHERE EmpID = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmployeeRowMapper());

        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getEmployeeById " + id);
        }
    }

    public Employee getEmployeeByLastName(String lastName) {
        try {
            String sql = "SELECT * FROM employees WHERE LastName = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{lastName}, new EmployeeRowMapper());

        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getEmployeeByLastName " + lastName);
        }
    }

    public void deleteEmployeeById(int id){
        try {
            jdbcTemplate.update("delete from employees where EmpID = ?", id);
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in deleteEmployeeById " + id);
        }
    }

    public Employee saveEmployee(Employee employee){
        try {
            jdbcTemplate.update("Insert into employees (BranchID, FirstName, LastName) values (?,?,?)",
                    employee.getBranchID(), employee.getFirstName(), employee.getLastName());
            int id = jdbcTemplate.queryForObject(
                    "select max(EmpID) from employees", Integer.class);
            employee.setEmpID(id);
            return employee;
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in saveEmployee ");
        }
    }

    public void updateEmployee(int id, Employee employee){
        try {
            jdbcTemplate.update("UPDATE employees set BranchID=?, FirstName=?, LastName=? WHERE EmpID=?",
                    employee.getBranchID(), employee.getFirstName(), employee.getLastName(), id);
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in updateEmployee " + id);
        }
    }

// TO DELETE!!! GIVES ONLY 1 OBJECT of EMPLOYEE (NOT A LIST)
//    public Employee getEmployeeByBranch(int branchID) {
//        try {
//            String sql = "SELECT employees.EmpID employeeID, employees.FirstName employeeFirstName, employees.LastName employeeLastName," +
//                    " branches.BranchID branchID, branches.BranchName branchName from " +
//                    " employees, branches WHERE employees.BranchID=branches.BranchID and branches.BranchID = ?";
//
//            EmployeeBranch employeeBranch = jdbcTemplate.queryForObject(sql, new Object[]{branchID}, new EmployeeBranchRowMapper());
//            if (employeeBranch != null) {
//                return new Employee(employeeBranch.getEmpID(), employeeBranch.getBranchID(), employeeBranch.getFirstName(), employeeBranch.getLastName(),
//                        new Branch(employeeBranch.getBranchName()));
//            }
//        } catch (Exception exc) {
//            throw new DatabaseException("an exception happened in getEmployeeByBranch " + branchID);
//        }
//        return null;
//    }


}
