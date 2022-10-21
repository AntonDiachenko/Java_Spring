package com.jac.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipOrderFull {
    private int shipOrderID;
    private String custFirstName;
    private String custLastName;
    private int branchID;
    private String branchName;
    private int empID;
    private String empFirstName;
    private String empLastName;
    private String shipTypeDescrip;
    private String shipAddress;

}
