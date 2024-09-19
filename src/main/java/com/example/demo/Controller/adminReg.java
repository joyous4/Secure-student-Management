package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Admin;
import com.example.demo.Service.adminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class adminReg {

    @Autowired
    private adminService adminService;
    @GetMapping("/register")
    public String getMethodName() {
        return "shh ! private";
    }
    
    @PostMapping("/register")
    public String registerMethodName(@RequestBody Admin admin) {
        adminService.createAdmin(admin);
        return "admin added";
    }
    
    @PostMapping("/login")
    public String loginMethodName(@RequestBody Admin admin) {
        return adminService.verify(admin);
    }
}
