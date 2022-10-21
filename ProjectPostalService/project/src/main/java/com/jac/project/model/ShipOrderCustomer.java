package com.jac.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipOrderCustomer {
    private int custID;
    private String firstName;
    private String lastName;
    private int shipOrderID;
    public String branchName;
    private String shipTypeDescrip;
    private String shipAddress;


}
