package com.parcelflow.model;

public class Shipment {
    //we make them private since we dont want them to be accessed by any other class and change it to an invalid number
    private Long id;
    private String trackingNumber;
    private double weight;
    private ShipmentStatus status;

    public Shipment (Long id, String trackingNumber, double weight){
        this.id = id;
        this.trackingNumber = trackingNumber;
        this.weight = weight;
        this.status = ShipmentStatus.CREATED; //when a new shipment is created, it automaticlly has a CREATED state
    }
    public Long getId(){
        return id;
    }
    public String getTrackingNumber(){
        return trackingNumber;
    }
    public double getWeight(){
        return weight;
    }
    public ShipmentStatus getStatus(){
        return status;
    }

}
