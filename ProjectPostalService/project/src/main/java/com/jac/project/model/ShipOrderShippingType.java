package com.jac.project.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipOrderShippingType {
    private String shipTypeDescrip;
    private int shipOrderID;
    private String shipAddress;
    private String firstName;
    private String lastName;
    public String branchName;

}
