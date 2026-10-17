package com.parcelflow;

import com.parcelflow.model.Customer;
import com.parcelflow.model.Shipment;
import com.parcelflow.model.ShipmentStatus;
import com.parcelflow.model.TrackingEvent;

public class ParcelFlowDemo {

    public static void main(String[] args) {

        Customer sender = new Customer(
                1L,
                "Sara",
                "sara@email.com",
                "70123456"
        );

        Customer receiver = new Customer(
                2L,
                "John",
                "john@email.com",
                "71123456"
        );

        Shipment shipment = new Shipment(
                1L,
                "PF-1001",
                2.5,
                sender,
                receiver
        );

        System.out.println("Initial status: " + shipment.getStatus());

        shipment.changeStatus(
                ShipmentStatus.PICKED_UP,
                "Package collected from sender"
        );

        shipment.changeStatus(
                ShipmentStatus.AT_ORIGIN_HUB,
                "Package arrived at origin hub"
        );

        System.out.println("Current status: " + shipment.getStatus());
/* 
        shipment.changeStatus(
        ShipmentStatus.DELIVERED,
        "Trying to deliver immediately"
    );*/
        System.out.println("\nTracking History:");

        for (TrackingEvent event : shipment.getTrackingHistory()) {
            System.out.println(
                    event.getTimestamp()
                    + " - "
                    + event.getStatus()
                    + " - "
                    + event.getDescription()
            );
        }
    }
}