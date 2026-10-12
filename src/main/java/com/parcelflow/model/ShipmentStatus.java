package com.parcelflow.model;

public enum ShipmentStatus { //set of possible statuses for each shipment
    CREATED,
    PICKED_UP,
    AT_ORIGIN_HUB,
    IN_TRANSIT,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED
;
//add a method to the enum which returns True if transitioning from one status to the other is allowed and false otherwise
    public boolean canTransitionTo(ShipmentStatus nextStatus){
        return switch (this){ //this refers to the current enum value (the current status)
            case CREATED -> nextStatus == PICKED_UP || nextStatus == CANCELLED;
            case PICKED_UP -> nextStatus == AT_ORIGIN_HUB;
            case AT_ORIGIN_HUB -> nextStatus == IN_TRANSIT;
            case IN_TRANSIT -> nextStatus == OUT_FOR_DELIVERY;
            case OUT_FOR_DELIVERY -> nextStatus == DELIVERED;
            case DELIVERED, CANCELLED -> false;
        };
    }

    }
