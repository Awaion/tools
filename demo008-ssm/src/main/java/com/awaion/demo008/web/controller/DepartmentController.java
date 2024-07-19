package com.awaion.demo008.web.controller;

import com.awaion.demo008.domain.Department;
import com.awaion.demo008.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {
    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/selectListForEmployeeForm")
    @ResponseBody
    public List<Department> selectListForEmployeeForm() {
        List<Department> result = departmentService.selectAll();
        return result;
    }
}
