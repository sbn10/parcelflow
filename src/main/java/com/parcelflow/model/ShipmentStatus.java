package com.parcelflow.model;

public enum ShipmentStatus { //set of possible statuses for each shipment
    CREATED,
    PICKED_UP,
    AT_ORIGIN_HUB,
    IN_TRANSIT,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED
}
