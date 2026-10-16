package com.parcelflow.model; 

public class Customer { //for the customer objects which will send and receive shipments 
    private Long id;
    private String name;
    private String email;
    private String phoneNumber; //we don't store it as Long since we dont perform mathematical operations on it

    public Customer (Long id, String name, String email, String phoneNumber){ 
        //four pieces of info stored for each object: id, name, email, phone number
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
}
