package com.jac.project.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipOrder {
    private int shipOrderID;
    private int branchID;
    private int custID;
    private int empID;
    private int shipTypeID;
    private String shipAddress;

    private Branch branch;
    private Customer customer;
    private Employee employee;
    private ShippingType shippingType;

    public ShipOrder(int shipOrderID, int branchID, int custID, int empID, int shipTypeID, String shipAddress) {
        this.shipOrderID = shipOrderID;
        this.branchID = branchID;
        this.custID = custID;
        this.empID = empID;
        this.shipTypeID = shipTypeID;
        this.shipAddress = shipAddress;
    }
}
