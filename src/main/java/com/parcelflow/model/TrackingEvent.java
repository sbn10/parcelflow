package com.parcelflow.model;

import java.time.LocalDateTime;

public class TrackingEvent {
    
    private Long id;
    private ShipmentStatus status;
    private String description;
    private LocalDateTime timestamp;

    public TrackingEvent(Long id, ShipmentStatus status, String description, LocalDateTime timestamp){
        this.id = id;
        this.status = status;
        this.description = description;
        this.timestamp = timestamp; //store date and time 
    }
    public Long getId(){
        return id;
    }
    public ShipmentStatus getStatus(){
        return status;
    }
    public String getDescription(){
        return description;
    }
    public LocalDateTime getTimestamp(){
        return timestamp;
    }


}
