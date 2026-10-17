package com.parcelflow.model;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

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
    private DeliveryAgent pickupAgent;
    private DeliveryAgent deliveryAgent;
    private List<TrackingEvent> trackingHistory; //contains a collection of multiple tracking events 

    public Shipment (Long id, String trackingNumber, double weight, Customer sender, Customer receiver){
        this.id = id;
        this.trackingNumber = trackingNumber;
        this.weight = weight;
        this.status = ShipmentStatus.CREATED; //when a new shipment is created, it automaticlly has a CREATED state
        this.sender = sender;
        this.receiver = receiver;
        this.trackingHistory = new ArrayList<>();
        this.trackingHistory.add(
            new TrackingEvent(
                null,
                ShipmentStatus.CREATED,
                "Shipment created",
                LocalDateTime.now()
            )
        );
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
    public DeliveryAgent getPickupAgent() {
    return pickupAgent;
    }

    public DeliveryAgent getDeliveryAgent() {
        return deliveryAgent;
    }
    public List<TrackingEvent> getTrackingHistory(){
        return trackingHistory;
    }
    
    //method that checks if the status transition is permissible and updates tracking history accordingly 
    public void changeStatus(ShipmentStatus newStatus, String description){
        if (!status.canTransitionTo(newStatus)){
            throw new IllegalStateException(
                "Cannot change shipment status from "+status+" to "+newStatus
            );
        }
        this.status = newStatus;
        TrackingEvent event = new TrackingEvent(
            null, newStatus, description, LocalDateTime.now()
        );
        trackingHistory.add(event);
    }

    //method for cancellation after being CREATED 
    public void cancel(String reason) {

        if (this.status != ShipmentStatus.CREATED) {
            throw new IllegalStateException(
                "Shipment can only be cancelled while it is CREATED"
            );
        }

        this.changeStatus(
            ShipmentStatus.CANCELLED,
            "Shipment cancelled: " + reason
        );
}

    //method to assign PICKED_UP to a package 
    public void pickUp(DeliveryAgent agent) {

        if (this.status != ShipmentStatus.CREATED) {
            throw new IllegalStateException(
                "Shipment can only be picked up when it is CREATED"
            );
        }

        this.changeStatus(
        ShipmentStatus.PICKED_UP,
        "Package picked up by " + agent.getName()
    );
        this.pickupAgent = agent;

}


    //method that changes the shipment status to AT_ORIGIN_HUB or AT_DESTINATION_HUB and allocates the new hub 
    public void arriveAtHub(Hub hub, ShipmentStatus hubStatus, String description){
        if (hubStatus != ShipmentStatus.AT_ORIGIN_HUB && hubStatus != ShipmentStatus.AT_DESTINATION_HUB){
            throw new IllegalArgumentException(
                "Hub status must be AT_ORIGIN_HUB or AT_DESTINATION_HUB"
            );
        }
        changeStatus(hubStatus, description);
        this.currentHub = hub;

    }

    //method to handle IN_TRANSIT from origin hub to destination hub 
    public void startTransit() {

        if (this.status != ShipmentStatus.AT_ORIGIN_HUB) {
            throw new IllegalStateException(
                "Shipment can only enter transit from the origin hub"
            );
        }

        this.changeStatus(
            ShipmentStatus.IN_TRANSIT,
            "Package departed origin hub"
        );
}

    //method that checks the current status then updates status to out for delivery and assigns the agent
    public void assignDeliveryAgent(DeliveryAgent agent){
        if (this.status != ShipmentStatus.AT_DESTINATION_HUB){
            throw new IllegalStateException(
                "Delivery agent can only be assigned when shipment is at destination hub"
            );
        }
        changeStatus(ShipmentStatus.OUT_FOR_DELIVERY, "Shipment assigned to delivery agent "+agent.getName());
        this.deliveryAgent = agent;
    }

    //method to mark a package as delivered to the receiver after being out for delivery 
    public void markDelivered(String description){
        if (this.status != ShipmentStatus.OUT_FOR_DELIVERY){
            throw new IllegalStateException(
                "Shipment can only be delivered when it is out for delivery"
            );
        }
        changeStatus(ShipmentStatus.DELIVERED, description);
    }


}
