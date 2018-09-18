package com.jason.websocket.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STUDENT_ID")
	private Long id;
    private String firstname, lastname, email;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "CLASSROOM_ID")
	private ClassRoom classRoom;
 
    private Student() {}
 
    public Student(String firstname, String lastname, String email, ClassRoom classRoom) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.classRoom = classRoom;
    }
	
	public Long getId() {
		
		return id;
	}
	
	public void setId(Long id) {
		
		this.id = id;
	}
	
	public String getFirstname() {
		
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		
		this.firstname = firstname;
	}
	
	public String getLastname() {
		
		return lastname;
	}
	
	public void setLastname(String lastname) {
		
		this.lastname = lastname;
	}
	
	public String getEmail() {
		
		return email;
	}
	
	public void setEmail(String email) {
		
		this.email = email;
	}
	
	public ClassRoom getClassRoom() {
		
		return classRoom;
	}
	
	public void setClassRoom(ClassRoom classRoom) {
		
		this.classRoom = classRoom;
	}
}