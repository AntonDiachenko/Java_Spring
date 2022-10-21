package com.jac.project.repository.model;


import com.jac.project.model.ShipOrderFull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipOrderFullRowMapper implements RowMapper<ShipOrderFull> {

    @Override
    public ShipOrderFull mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShipOrderFull shipOrderFull = new ShipOrderFull();

        shipOrderFull.setShipOrderID (rs.getInt("ShipOrderID"));
        shipOrderFull.setCustFirstName(rs.getString("CustFirstName"));
        shipOrderFull.setCustLastName(rs.getString("CustLastName"));
        shipOrderFull.setBranchID(rs.getInt("BranchID"));
        shipOrderFull.setBranchName(rs.getString("BranchName"));
        shipOrderFull.setEmpID(rs.getInt("EmpID"));
        shipOrderFull.setEmpFirstName(rs.getString("EmpFirstName"));
        shipOrderFull.setEmpLastName(rs.getString("EmpLastName"));
        shipOrderFull.setShipTypeDescrip(rs.getString("ShipTypeDescrip"));
        shipOrderFull.setShipAddress(rs.getString("ShipAddress"));

        return shipOrderFull;
    }


}
