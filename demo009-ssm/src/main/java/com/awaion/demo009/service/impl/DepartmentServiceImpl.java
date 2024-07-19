package com.awaion.demo009.service.impl;

import com.awaion.demo009.domain.Department;
import com.awaion.demo009.mapper.DepartmentMapper;
import com.awaion.demo009.service.IDepartmentService;
import lombok.Setter;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {
    @Setter
    private DepartmentMapper departmentMapper;

    @Override
    public void save(Department entity) {
        departmentMapper.save(entity);
    }

    @Override
    public void update(Department entity) {
        departmentMapper.update(entity);
    }

    @Override
    public void delete(Long id) {
        departmentMapper.delete(id);
    }

    @Override
    public Department get(Long id) {
        return departmentMapper.get(id);
    }

    @Override
    public List<Department> listAll() {
        return departmentMapper.listAll();
    }

}
