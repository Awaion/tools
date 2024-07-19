package com.awaion.demo009.service;

import com.awaion.demo009.domain.Department;

import java.util.List;

public interface IDepartmentService {
    void save(Department entity);

    void update(Department entity);

    void delete(Long id);

    Department get(Long id);

    List<Department> listAll();
}
