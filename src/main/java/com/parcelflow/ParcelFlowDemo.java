package com.parcelflow;

import com.parcelflow.model.Customer;
import com.parcelflow.model.DeliveryAgent;
import com.parcelflow.model.Hub;
import com.parcelflow.model.Shipment;
import com.parcelflow.model.TrackingEvent;

public class ParcelFlowDemo {

    public static void main(String[] args) {

        // ----------------------------
        // Create customers
        // ----------------------------

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


        // ----------------------------
        // Create hubs
        // ----------------------------

        Hub beirutHub = new Hub(
                1L,
                "Beirut Distribution Hub",
                "Beirut"
        );

        Hub tripoliHub = new Hub(
                2L,
                "Tripoli Distribution Hub",
                "Tripoli"
        );


        // ----------------------------
        // Create delivery agents
        // ----------------------------

        DeliveryAgent pickupAgent = new DeliveryAgent(
                1L,
                "Ahmad",
                "ahmad@parcelflow.com",
                "70111111"
        );

        DeliveryAgent deliveryAgent = new DeliveryAgent(
                2L,
                "Maya",
                "maya@parcelflow.com",
                "70222222"
        );


        // =========================================================
        // TEST 1: COMPLETE SUCCESSFUL DELIVERY
        // =========================================================

        System.out.println("===== TEST 1: SUCCESSFUL DELIVERY =====");

        Shipment shipment = new Shipment(
                1L,
                "PF-1001",
                2.5,
                sender,
                receiver
        );

        System.out.println("Initial status: " + shipment.getStatus());


        // CREATED -> PICKED_UP
        shipment.pickUp(pickupAgent);

        System.out.println(
                "Pickup agent: " + shipment.getPickupAgent().getName()
        );


        // PICKED_UP -> AT_ORIGIN_HUB
        shipment.arriveAtHub(
                beirutHub,
                com.parcelflow.model.ShipmentStatus.AT_ORIGIN_HUB,
                "Package arrived at Beirut Distribution Hub"
        );

        System.out.println(
                "Current hub: " + shipment.getCurrentHub().getName()
        );


        // AT_ORIGIN_HUB -> IN_TRANSIT
        shipment.startTransit();


        // IN_TRANSIT -> AT_DESTINATION_HUB
        shipment.arriveAtHub(
                tripoliHub,
                com.parcelflow.model.ShipmentStatus.AT_DESTINATION_HUB,
                "Package arrived at Tripoli Distribution Hub"
        );

        System.out.println(
                "Current hub: " + shipment.getCurrentHub().getName()
        );


        // AT_DESTINATION_HUB -> OUT_FOR_DELIVERY
        shipment.assignDeliveryAgent(deliveryAgent);

        System.out.println(
                "Final delivery agent: "
                        + shipment.getDeliveryAgent().getName()
        );


        // OUT_FOR_DELIVERY -> DELIVERED
        shipment.markDelivered(
                "Package successfully delivered to receiver"
        );


        System.out.println(
                "Final status: " + shipment.getStatus()
        );


        // Print full shipment history
        System.out.println("\nTracking History:");

        for (TrackingEvent event : shipment.getTrackingHistory()) {
            System.out.println(
                    event.getTimestamp()
                            + " | "
                            + event.getStatus()
                            + " | "
                            + event.getDescription()
            );
        }


        // =========================================================
        // TEST 2: CANCELLATION
        // =========================================================

        System.out.println("\n===== TEST 2: CANCELLATION =====");

        Shipment cancelledShipment = new Shipment(
                2L,
                "PF-1002",
                1.5,
                sender,
                receiver
        );

        cancelledShipment.cancel(
                "Sender requested cancellation"
        );

        System.out.println(
                "Cancelled shipment status: "
                        + cancelledShipment.getStatus()
        );

        System.out.println("Cancellation history:");

        for (TrackingEvent event :
                cancelledShipment.getTrackingHistory()) {

            System.out.println(
                    event.getTimestamp()
                            + " | "
                            + event.getStatus()
                            + " | "
                            + event.getDescription()
            );
        }


        // =========================================================
        // TEST 3: INVALID OPERATION
        // =========================================================

        System.out.println("\n===== TEST 3: INVALID OPERATION =====");

        Shipment invalidShipment = new Shipment(
                3L,
                "PF-1003",
                4.0,
                sender,
                receiver
        );

        try {

            // This should fail because the shipment
            // has not reached the destination hub.
            invalidShipment.assignDeliveryAgent(deliveryAgent);

        } catch (IllegalStateException exception) {

            System.out.println(
                    "Operation rejected correctly:"
            );

            System.out.println(
                    exception.getMessage()
            );
        }

        System.out.println(
                "Shipment remains: "
                        + invalidShipment.getStatus()
        );
    }
}