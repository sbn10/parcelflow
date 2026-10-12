package com parcelflow.model;

public class Shipment { //for our shipment objects that will be delivered 
    //we make them private since we dont want them to be accessed by any other class and change it to an invalid number
    //four pieces of info stored for each object: id, tracking number, weight, and shipment status
    private Long id;
    private String trackingNumber;
    private double weight;
    private ShipmentStatus status;

    private Customer sender;
    private Customer receiver;

    private Hub currentHub;
    private DeliveryAgent assignedAgent;

    private List<TrackingEvent> trackingHistory; //contains a collection of multiple tracking events 

    public Shipment (Long id, String trackingNumber, double weight, Customer sender, Customer receiver){
        this.id = id;
        this.trackingNumber = trackingNumber;
        this.weight = weight;
        this.status = ShipmentStatus.CREATED; //when a new shipment is created, it automaticlly has a CREATED state
        this.sender = sender;
        this.receiver = receiver;
        this.trackingHistory = new ArrayList<>();
        //currentHub and assignedAgent aren't in the constructor because at the moment of creating a Shipment object
        //it hasn't been assigned a hub or delivery agent 
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
    public Customer getSender(){
        return sender;
    }
    public Customer getReceiver(){
        return receiver;
    }
    public Hub getCurrentHub(){
        return currentHub;
    }
    public DeliveryAgent getAssignedAgent(){
        return assignedAgent;
    }
    public List<TrackingEvent> getTrackingHistory(){
        return trackingHistory;
    }


}
