package com.jac.project.repository.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeBranchRowMapper implements RowMapper<EmployeeBranch> {

    @Override
    public EmployeeBranch mapRow(ResultSet rs, int rowNum) throws SQLException {
        EmployeeBranch employeeBranch = new EmployeeBranch();

        employeeBranch.setEmpID(rs.getInt("employeeID"));
        employeeBranch.setFirstName(rs.getString("employeeFirstName"));
        employeeBranch.setLastName(rs.getString("employeeLastName"));
        employeeBranch.setBranchID(rs.getInt("branchID"));
        employeeBranch.setBranchName(rs.getString("branchName"));

        return employeeBranch;
    }
}
