package com.example.demo.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.example.demo.Entity.Admin;
import com.example.demo.Entity.UserPrincipal;
import com.example.demo.Repo.MyUserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MyUserRepo myUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = myUserRepo.findByUsername(username);
        if(admin==null){
            System.out.println("User not there");
            throw new UsernameNotFoundException(username+" is not found");
        }
        return new UserPrincipal(admin);
    }

}
