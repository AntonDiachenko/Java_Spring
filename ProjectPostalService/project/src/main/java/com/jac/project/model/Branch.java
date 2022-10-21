package com.jac.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
    private int branchID;
    private String branchName;

    public Branch(String branchName) {
        this.branchName = branchName;
    }
}
