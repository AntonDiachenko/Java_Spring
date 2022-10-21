package com.jac.project.service;

import com.jac.project.exception.DatabaseException;
import com.jac.project.exception.ShipOrderExistException;
import com.jac.project.exception.ShipOrderNotFoundException;
import com.jac.project.model.ShipOrder;
import com.jac.project.model.ShipOrderCustomer;
import com.jac.project.model.ShipOrderFull;
import com.jac.project.model.ShipOrderShippingType;
import com.jac.project.repository.ShipOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipOrderService {

    ArrayList<ShipOrder> shipOrders = new ArrayList<>();
    ArrayList<ShipOrderFull> shipOrderFulls = new ArrayList<>();

    @Autowired
    private ShipOrderRepository shipOrderRepository;

    public List<ShipOrderFull> getShipOrderFulls() { return shipOrderRepository.getAllShipOrderFulls(); }

//    public List<ShipOrder> getShipOrders() { return shipOrderRepository.getAllShipOrders(); }

    public List<ShipOrderCustomer> getShipOrdersByCustomerID(int id){
        return shipOrderRepository.getShipOrdersByCustomerID(id);
    }

    public List<ShipOrderShippingType> getShipOrdersByShippingType(String shipType){
        return shipOrderRepository.getShipOrdersByShippingType(shipType);
    }

    public ShipOrder getShipOrderById(int id) {
        try {
            return shipOrderRepository.getShipOrderById(id);
        } catch (DatabaseException exc) {
            return null;
        }
    }

    public void deleteShipOrder(int id) {
        ShipOrder fetchedShipOrder = getShipOrderById(id);
        if (fetchedShipOrder == null) {
            throw new ShipOrderNotFoundException("the shiporder does not exist " + id);
        }
        shipOrderRepository.deleteShipOrderById(id);
    }

    public ShipOrder saveShipOrder(ShipOrder shipOrder) {
        if (getShipOrderById(shipOrder.getShipOrderID()) == null) {
            return shipOrderRepository.saveShipOrder(shipOrder);
        } else {
            throw new ShipOrderExistException("the shiporder with the same id exists");
        }
    }



}
