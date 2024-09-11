package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Student;
@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{
    List<Student> findByName(String name);
}