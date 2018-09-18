package com.jason.websocket.domain;

import javax.persistence.*;

@Entity
public class ClassRoom {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CLASSROOM_ID")
    private Long   id;
    private String name, floor;
    private int capacity;
    
//    @OneToMany(mappedBy = "classRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<Student> students;
    
    private ClassRoom() {}
    
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
    
//    public Set<Student> getStudents() {
//
//        return students;
//    }
//
//    public void setStudents(Set<Student> students) {
//
//        this.students = students;
//    }
}