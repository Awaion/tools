package com.awaion.demo009.web.action;

import com.awaion.demo009.domain.Department;
import com.awaion.demo009.service.IDepartmentService;
import com.awaion.demo009.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class DepartmentAction extends BaseAction {
    @Setter
    private IDepartmentService departmentService;
    @Getter
    private Department department = new Department();

    @Override
    public String execute() throws Exception {
        List<Department> results = departmentService.listAll();
        //数据放到域中.
        putContext("results", results);
        return LIST;
    }

    @RequiredPermission("部门编辑")
    public String input() throws Exception {
        if (department.getId() != null) {
            //此次请求是编辑的请求,查询对应的数据,并回显到表单中.
            department = departmentService.get(department.getId());
        }
        return INPUT;
    }

    @RequiredPermission("部门保存/更新")
    public String saveOrUpdate() throws Exception {

        if (department.getId() != null) {
            //如果有ID是编辑
            departmentService.update(department);
        } else {
            //如果没有ID是新增
            departmentService.save(department);
        }

        return SUCCESS;
    }

    @RequiredPermission("部门删除")
    public String delete() throws Exception {
        departmentService.delete(department.getId());
        return SUCCESS;
    }
}
