package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Admin;
import com.example.demo.Repo.MyUserRepo;

@Service
public class adminService {

    @Autowired
    private MyUserRepo repo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTservice jwTservice;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12); //strength is 12 set for the encoder

    public void createAdmin(Admin admin) {
        admin.setPassword(encoder.encode(admin.getPassword()));
        repo.save(admin);
    }

    public String verify(Admin admin) {
        Authentication authentication = 
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword()));

        if(authentication.isAuthenticated())
            return jwTservice.generateToken(admin.getUsername());
        return "Fail";
    }

}
