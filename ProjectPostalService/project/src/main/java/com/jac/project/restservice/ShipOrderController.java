package com.jac.project.restservice;

import com.jac.project.exception.ShipOrderExistException;
import com.jac.project.exception.ShipOrderNotFoundException;
import com.jac.project.model.ShipOrder;
import com.jac.project.model.ShipOrderCustomer;
import com.jac.project.model.ShipOrderFull;
import com.jac.project.model.ShipOrderShippingType;
import com.jac.project.service.ShipOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 45000)
@RequestMapping("/")
public class ShipOrderController {

    @Autowired
    private ShipOrderService shipOrderService;

    @GetMapping("shiporder")
    public ResponseEntity<List<ShipOrderFull>> getAllShipOrderFulls(){
        return new ResponseEntity<>(shipOrderService.getShipOrderFulls(), HttpStatus.OK);
    }

    @GetMapping("shiporder/id/{shipOrderID}")
    public ResponseEntity<ShipOrder> getShipOrderById(@PathVariable int shipOrderID){
        return new ResponseEntity<>(shipOrderService.getShipOrderById(shipOrderID), HttpStatus.OK);
    }

    @GetMapping("shiporder/customer/{custID}")
    public ResponseEntity<List<ShipOrderCustomer>> getShipOrdersByCustomerID(@PathVariable int custID){
        return new ResponseEntity<>(shipOrderService.getShipOrdersByCustomerID(custID), HttpStatus.OK);
    }

    @GetMapping("shiporder/shippingtype/{shipType}")
    public ResponseEntity<List<ShipOrderShippingType>> getShipOrdersByShippingType(@PathVariable String shipType){
        return new ResponseEntity<>(shipOrderService.getShipOrdersByShippingType(shipType), HttpStatus.OK);
    }

    @DeleteMapping("shiporder/id/{shipOrderID}")
    public ResponseEntity deleteShipOrder(@PathVariable int shipOrderID){
        try{
            shipOrderService.deleteShipOrder(shipOrderID);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        catch (ShipOrderNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("shiporder")
    public ResponseEntity<ShipOrder> createShipOrder(@RequestBody ShipOrder shipOrder)
    {
        try{
            return new ResponseEntity<>(shipOrderService.saveShipOrder(shipOrder), HttpStatus.CREATED);
        }
        catch (ShipOrderExistException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
