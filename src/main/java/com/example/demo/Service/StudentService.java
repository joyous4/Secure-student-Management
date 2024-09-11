package com.example.demo.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.dto;
import com.example.demo.Entity.Student;
import com.example.demo.Repo.StudentRepo;

@Service
public class StudentService {
    @Autowired
    private StudentRepo repo;

    @Autowired
    private ModelMapper modelMapper;

    public List<dto> getAll(){
        List<Student> students = repo.findAll();

        List<dto> sDto = students.stream()
                             .map(student -> modelMapper.map(student, dto.class))
                             .collect(Collectors.toList());
        return sDto;
    }
    public dto getbyId(int id){
        Student student= repo.findById(id).orElseThrow(()->new RuntimeException("not found"));
        return modelMapper.map(student, dto.class);
    }
    public void createStudent(dto stdto){
        Student student = modelMapper.map(stdto, Student.class);
        System.out.println(student.getPassWord());
        repo.save(student);
    }
    public List<dto> getName(String name){
        List<Student> students = repo.findByName(name);
        List<dto> sDto = students.stream()
                             .map(student -> modelMapper.map(student, dto.class))
                             .collect(Collectors.toList());
        return sDto;
    }
    public void updateStudent(int id, dto stdto){
        Student student1 = repo.findById(id).orElseThrow(()->new RuntimeException("not found"));
        student1.setName(stdto.getName());
        student1.setRoll(stdto.getRoll());
        repo.save(student1);
    }
    public void deleteStudent(int id){
        repo.deleteById(id);
    }
}