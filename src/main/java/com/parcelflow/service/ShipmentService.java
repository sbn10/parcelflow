package com.parcelflow.service;

import com.parcelflow.model.Shipment;
import com.parcelflow.dto.CreateShipmentRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service //Spring notation which means that ShipmentService is a service and when the application runs, a ShipmentService object is created
public class ShipmentService {

    private final List<Shipment> shipments = new ArrayList<>(); //temporary database replacement 
    private Long nextId = 1L; //id counter 
    private int nextTrackingNumber = 1001; //trackingNumber counter

    public List<Shipment> getAllShipments() {
        return shipments;
    }

    public Shipment createShipment(CreateShipmentRequest request) {

        Long id = nextId++;

        String trackingNumber =
                "PF-" + nextTrackingNumber++;

        Shipment shipment = new Shipment(
                id,
                trackingNumber,
                request.getWeight(),
                request.getSender(),
                request.getReceiver()
        );

        shipments.add(shipment);

        return shipment;
}
}
