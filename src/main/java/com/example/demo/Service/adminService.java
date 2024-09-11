package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Admin;
import com.example.demo.Repo.MyUserRepo;

@Service
public class adminService {

    @Autowired
    private MyUserRepo repo;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12); //strength is 12 set for the encoder

    public void createAdmin(Admin admin) {
        admin.setPassword(encoder.encode(admin.getPassword()));
        repo.save(admin);
    }

}
