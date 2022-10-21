package com.jac.project.repository.model;

import com.jac.project.model.ShipOrderCustomer;
import com.jac.project.model.ShipOrderShippingType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipOrderShippingTypeRowMapper implements RowMapper<ShipOrderShippingType> {

    @Override
    public ShipOrderShippingType mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShipOrderShippingType shipOrderShippingType = new ShipOrderShippingType();

        shipOrderShippingType.setShipTypeDescrip(rs.getString("ShipType"));
        shipOrderShippingType.setShipOrderID(rs.getInt("ShipOrderID"));
        shipOrderShippingType.setShipAddress(rs.getString("ShipAddress"));
        shipOrderShippingType.setFirstName(rs.getString("FirstName"));
        shipOrderShippingType.setLastName(rs.getString("LastName"));
        shipOrderShippingType.setBranchName(rs.getString("BranchName"));

        return shipOrderShippingType;
    }
}
