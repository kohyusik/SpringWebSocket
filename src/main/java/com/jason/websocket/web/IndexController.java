package com.jason.websocket.web;

import com.jason.websocket.domain.Student;
import com.jason.websocket.domain.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    
    
    final
    StudentRepository studentRepository;
    
    @Autowired
    public IndexController(StudentRepository studentRepository) {
        
        this.studentRepository = studentRepository;
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
}