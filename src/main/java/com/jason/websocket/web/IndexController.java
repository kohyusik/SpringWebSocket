package com.jason.websocket.web;

import com.jason.websocket.domain.ClassRoom;
import com.jason.websocket.domain.ClassRoomRepository;
import com.jason.websocket.domain.Student;
import com.jason.websocket.domain.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    
    
    private final StudentRepository   studentRepository;
    private final ClassRoomRepository classRoomRepository;
    
    @Autowired
    public IndexController(StudentRepository studentRepository, ClassRoomRepository classRoomRepository) {
        
        this.studentRepository = studentRepository;
        this.classRoomRepository = classRoomRepository;
    }
    
    @RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }
    
    @RequestMapping(value = "/ws")
    public String indexWs() {
        return "index_ws.html";
    }
    
    @RequestMapping(value = "/api/students")
    @ResponseBody
    public Iterable<Student> students() {
        return studentRepository.findAll();
    }
    
    @RequestMapping(value = "/api/classroom")
    @ResponseBody
    public Iterable<ClassRoom> classRooms() {
        return classRoomRepository.findAll();
    }
//
//    @RequestMapping(value = "/api/classroom")
//    @ResponseBody
//    public Iterable<ClassRoom> classRooms() {
//        return classRoomRepository.findAll();
//    }
}