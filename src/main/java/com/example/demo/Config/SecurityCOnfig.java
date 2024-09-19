package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.filterPackage.JwtFilter;

import org.springframework.security.core.userdetails.User;


@Configuration
@EnableWebSecurity //dont go with default flow, do as i say
public class SecurityCOnfig {

    @Autowired
    private UserDetailsService myUserDetailsService;

    @Autowired
    private JwtFilter jwtFilter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(customizer -> customizer.disable());
        httpSecurity.authorizeHttpRequests(authorize -> authorize
                                            .requestMatchers("register").permitAll()
                                            .anyRequest().authenticated());
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    /*@Bean -> HardCoding, not working with database
    public UserDetailsService /// it works to authenticate or verify our user/// userDetailsService(){
        UserDetails user1 = User
                        .withDefaultPasswordEncoder()
                        .username("joy1")
                        .password("123")
                        .roles("ADMIN")
                        .build();
        UserDetails user2 = User
                        .withDefaultPasswordEncoder()
                        .username("joy2")
                        .password("123")
                        .roles("ADMIN")
                        .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }*/

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); -> for verifying not using encoder
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12)); // -> taking input of the password, then converting it to the same cipher text so that it can compare it with the database's password value
        provider.setUserDetailsService(myUserDetailsService);
        return provider;
    }

    //since we are using jwt, customizing the authetication, we will use authenticationManager, which was previously working behind the scene to call authenticationProvider and managing the authentications
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}
