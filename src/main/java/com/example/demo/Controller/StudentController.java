package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.dto;
import com.example.demo.Service.StudentService;

@RestController  //@Controller + @ResponseBody
@RequestMapping("/admin")
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping("/allStudent")
    public List<dto> getAll(){
        return service.getAll();
    }   
    @GetMapping("/student/{id}")
    public dto byId(@PathVariable int id){
        return service.getbyId(id);
    }  
    @GetMapping("/student/byName")
    public List<dto> byName(@RequestParam String name){
        return service.getName(name);
    } 
    @PostMapping("/student")
    public String postAll(@RequestBody dto stdto){
        service.createStudent(stdto);
        return "saved";
    } 
    @PutMapping("/student/{id}")
    public String update(@PathVariable int id, @RequestBody dto stdto){
        service.updateStudent(id, stdto);
        return "updated";
    }
    @DeleteMapping("/student/{id}")
    public String delete(@PathVariable int id){
        service.deleteStudent(id);
        return "deleted";
    }
}