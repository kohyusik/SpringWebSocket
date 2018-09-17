package com.jason.websocket.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ClassRoom {
    
    private @Id
    @GeneratedValue
            Long   id;
    private String name, floor;
    private int capacity;
    
    private ClassRoom() {
    
    }
    
    public ClassRoom(String name, String floor, int capacity) {
        
        this.name = name;
        this.floor = floor;
        this.capacity = capacity;
    }
    
    public Long getId() {
        
        return id;
    }
    
    public void setId(Long id) {
        
        this.id = id;
    }
    
    public String getName() {
        
        return name;
    }
    
    public void setName(String name) {
        
        this.name = name;
    }
    
    public String getFloor() {
        
        return floor;
    }
    
    public void setFloor(String floor) {
        
        this.floor = floor;
    }
    
    public int getCapacity() {
        
        return capacity;
    }
    
    public void setCapacity(int capacity) {
        
        this.capacity = capacity;
    }
}