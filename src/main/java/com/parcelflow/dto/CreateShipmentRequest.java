package com.parcelflow.dto;

import com.parcelflow.model.Customer;

public class CreateShipmentRequest {

    //the info we are allowing the API caller to provide 
    /*
    to create a shipment, give ParcelFlow:
    -weight
    -sender
    -receiver
    */
    private double weight;
    private Customer sender;
    private Customer receiver;

    public CreateShipmentRequest() { //Spring creates an empty objects then sets weight, sender, receiver 
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    public Customer getReceiver() {
        return receiver;
    }

    public void setReceiver(Customer receiver) {
        this.receiver = receiver;
    }
}

/*
Setters are included because we want to set the fields to the values given in the JSON message received
this is handled by Spring

*/