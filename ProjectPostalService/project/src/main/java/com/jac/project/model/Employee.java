package com.jac.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int empID;
    private int branchID;
    private String firstName;
    private String lastName;


    private Branch branch;

    public Employee(int empID, int branchID, String firstName, String lastName) {
        this.empID = empID;
        this.branchID = branchID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
