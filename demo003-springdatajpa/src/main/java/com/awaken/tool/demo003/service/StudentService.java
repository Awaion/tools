package com.awaken.tool.demo003.service;

import com.awaken.tool.demo003.model.Student;
import com.awaken.tool.demo003.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public void save(Student student) {
        repository.save(student);
    }

    public List<Student> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Student> getByNameAndAge(String name, Integer age) {
        return repository.getByNameAndAge(name, age);
    }

    public Long countByName(String name) {
        return repository.countByName(name);
    }
}
