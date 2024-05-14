package com.awaion.demo009.mapper;

import com.awaion.demo009.domain.Department;

import java.util.List;

public interface DepartmentMapper {
    void save(Department entity);

    void update(Department entity);

    void delete(Long id);

    Department get(Long id);

    List<Department> listAll();
}
