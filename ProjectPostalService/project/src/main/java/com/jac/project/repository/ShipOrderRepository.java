package com.jac.project.repository;

import com.jac.project.exception.DatabaseException;
import com.jac.project.model.ShipOrder;
import com.jac.project.model.ShipOrderCustomer;
import com.jac.project.model.ShipOrderFull;
import com.jac.project.model.ShipOrderShippingType;
import com.jac.project.repository.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShipOrderRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<ShipOrderFull> getAllShipOrderFulls(){

        try {
            String sql = "SELECT shiporders.ShipOrderID ShipOrderID, " +
                    " customers.FirstName CustFirstName, " +
                    " customers.LastName CustLastName, " +
                    " branches.BranchID BranchID, " +
                    " branches.BranchName BranchName, " +
                    " employees.EmpID EmpID, " +
                    " employees.FirstName EmpFirstName, " +
                    " employees.LastName EmpLastName, " +
                    " shippingtypes.ShipTypeDescrip ShipTypeDescrip, " +
                    " shiporders.ShipAddress ShipAddress " +
                    " from shiporders, customers, branches, employees, shippingtypes " +
                    " WHERE shiporders.CustID=customers.CustID and " +
                    " shiporders.BranchID=branches.BranchID and " +
                    " shiporders.EmpID=employees.EmpID and " +
                    " shiporders.ShipTypeID=shippingtypes.ShipTypeID ";

            List<ShipOrderFull> shipOrderFulls = jdbcTemplate.query(
                    sql,
                    new ShipOrderFullRowMapper());
            return shipOrderFulls;
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getAllShipOrders");
        }
    }

//   what is the difference between this and above?
//    public List<ShipOrder> getAllShipOrders(){
//        try {
//            return jdbcTemplate.query("SELECT * from shiporders",
//                    (rs, rowNum) -> new ShipOrder(rs.getInt("ShipOrderID"),
//                            rs.getInt("BranchID"),
//                            rs.getInt("CustID"),
//                            rs.getInt("EmpID"),
//                            rs.getInt("ShipTypeID"),
//                            rs.getString("ShipAddress")));
//        }catch (Exception exc){
//            throw new DatabaseException("an exception happened in getAllShipOrders");
//        }
//    }


    public List<ShipOrderShippingType> getShipOrdersByShippingType(String shipType){

        try {
            String sql = "SELECT shippingtypes.ShipTypeDescrip ShipType, " +
                    "shiporders.ShipOrderID ShipOrderID, " +
                    "shiporders.ShipAddress ShipAddress, " +
                    "customers.FirstName FirstName, " +
                    "customers.LastName LastName, " +
                    "branches.BranchName BranchName " +
                    "from shiporders, customers, branches, shippingtypes " +
                    "WHERE shiporders.CustID=customers.CustID and " +
                    "shiporders.BranchID=branches.BranchID and " +
                    "shiporders.ShipTypeID=shippingtypes.ShipTypeID and " +
                    "shippingtypes.ShipTypeDescrip = " + "'" + shipType + "'" +";";

            List<ShipOrderShippingType> shipOrderShippingTypes = jdbcTemplate.query(
                    sql,
                    new ShipOrderShippingTypeRowMapper());
            return shipOrderShippingTypes;
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getShipOrdersByCustomerID");
        }
    }

    public List<ShipOrderCustomer> getShipOrdersByCustomerID(int id){

        try {
            String sql = "SELECT shiporders.CustID CustomerID, customers.FirstName FirstName, customers.LastName LastName," +
                    " shiporders.ShipOrderID ShipOrderID, branches.BranchName BranchName, shippingtypes.ShipTypeDescrip ShipType," +
                    " shiporders.ShipAddress ShipAddress " +
                    "from shiporders, customers, branches, shippingtypes" +
                    " WHERE shiporders.CustID=customers.CustID and shiporders.BranchID=branches.BranchID and " +
                    "shiporders.ShipTypeID=shippingtypes.ShipTypeID" +
                    " and customers.CustID =" + id +";";

            List<ShipOrderCustomer> shipOrderCustomers = jdbcTemplate.query(
                    sql,
                    new ShipOrderCustomerRowMapper());
            return shipOrderCustomers;
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getShipOrdersByCustomerID");
        }
    }

    public ShipOrder getShipOrderById(int id) {
        try {
            String sql = "SELECT * FROM shiporders WHERE ShipOrderID = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ShipOrderRowMapper());

        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getShipOrderById " + id);
        }
    }

    public void deleteShipOrderById(int id){
        try {
            jdbcTemplate.update("delete from shiporders where ShipOrderID = ?", id);
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in deleteShipOrderById " + id);
        }
    }

    public ShipOrder saveShipOrder(ShipOrder shipOrder){
        try {
            jdbcTemplate.update("Insert into shiporders (BranchID, CustID, EmpID, ShipTypeID, ShipAddress) values (?,?,?,?,?)",
                    shipOrder.getBranchID(), shipOrder.getCustID(), shipOrder.getEmpID(), shipOrder.getShipTypeID(), shipOrder.getShipAddress());
            int id = jdbcTemplate.queryForObject(
                    "select max(ShipOrderID) from shiporders", Integer.class);
            shipOrder.setShipOrderID(id);
            return shipOrder;
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in saveShipOrder ");
        }
    }


}
