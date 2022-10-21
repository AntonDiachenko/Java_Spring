package com.jac.project.repository.model;


import com.jac.project.model.ShipOrder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipOrderRowMapper implements RowMapper<ShipOrder> {

    @Override
    public ShipOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShipOrder shipOrder = new ShipOrder();
        shipOrder.setShipOrderID(rs.getInt("ShipOrderID"));
        shipOrder.setBranchID(rs.getInt("BranchID"));
        shipOrder.setCustID(rs.getInt("CustID"));
        shipOrder.setEmpID(rs.getInt("EmpID"));
        shipOrder.setShipTypeID(rs.getInt("ShipTypeID"));
        shipOrder.setShipAddress(rs.getString("ShipAddress"));

        return shipOrder;
    }

}
