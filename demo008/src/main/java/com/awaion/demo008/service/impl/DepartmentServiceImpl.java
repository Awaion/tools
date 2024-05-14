package com.awaion.demo008.service.impl;

import com.awaion.demo008.domain.Department;
import com.awaion.demo008.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private com.awaion.demo008.mapper.DepartmentMapper DepartmentMapper;

    public int deleteByPrimaryKey(Long id) {
        return DepartmentMapper.deleteByPrimaryKey(id);
    }

    public int insert(Department record) {
        int count = DepartmentMapper.insert(record);
        return count;
    }

    public Department selectByPrimaryKey(Long id) {
        return DepartmentMapper.selectByPrimaryKey(id);
    }

    public List<Department> selectAll() {
        return DepartmentMapper.selectAll();
    }

    public int updateByPrimaryKey(Department record) {
        return DepartmentMapper.updateByPrimaryKey(record);
    }

}
