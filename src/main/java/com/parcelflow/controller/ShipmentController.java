package com.parcelflow.controller;

import com.parcelflow.model.Shipment;
import com.parcelflow.service.ShipmentService;

import com.parcelflow.dto.CreateShipmentRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //means that the below class receives HTTP requests and returns data as HTTP responses.
@RequestMapping("/shipments") //Defines the base URL for the controller
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping //when someone sends an HTTP GET request, the below method is executed 
    public List<Shipment> getAllShipments() {
        return shipmentService.getAllShipments();
    }

    @PostMapping //respond to an HTTP POST request
    //@RequestBody takes the JSON inside the HTTP request body and converts it into a CreateShipmentRequest Java object
    public Shipment createShipment(@RequestBody CreateShipmentRequest request) {
        return shipmentService.createShipment(request);
    }
    


}