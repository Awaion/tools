package com.awaken.tool.demo003.repository;

import com.awaken.tool.demo003.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // 关键字的搭配
    List<Student> findByName(String name);

    // 关键字的搭配
    List<Student> getByNameAndAge(String name, Integer age);

    // 关键字的搭配
    Long deleteByName(String name);

    // 关键字的搭配
    Long countByName(String name);
}
