package com.jac.project.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeBranch {
    private int empID;
    private String firstName;
    private String lastName;
    private int branchID;
    private String branchName;
}
