package com.jac.project.repository.model;

import com.jac.project.model.ShipOrderCustomer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipOrderCustomerRowMapper implements RowMapper<ShipOrderCustomer> {

    @Override
    public ShipOrderCustomer mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShipOrderCustomer shipOrderCustomer = new ShipOrderCustomer();

        shipOrderCustomer.setCustID(rs.getInt("CustomerID"));
        shipOrderCustomer.setFirstName(rs.getString("FirstName"));
        shipOrderCustomer.setLastName(rs.getString("LastName"));
        shipOrderCustomer.setShipOrderID(rs.getInt("ShipOrderID"));
        shipOrderCustomer.setBranchName(rs.getString("BranchName"));
        shipOrderCustomer.setShipTypeDescrip(rs.getString("ShipType"));
        shipOrderCustomer.setShipAddress(rs.getString("ShipAddress"));

        return shipOrderCustomer;
    }
}
