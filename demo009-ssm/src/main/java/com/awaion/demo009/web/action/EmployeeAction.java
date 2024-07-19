package com.awaion.demo009.web.action;

import com.awaion.demo009.domain.Employee;
import com.awaion.demo009.page.PageResult;
import com.awaion.demo009.query.EmployeeQueryObject;
import com.awaion.demo009.service.IDepartmentService;
import com.awaion.demo009.service.IEmployeeService;
import com.awaion.demo009.service.IRoleService;
import com.awaion.demo009.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authz.annotation.RequiresPermissions;

public class EmployeeAction extends BaseAction {
    @Setter
    private IEmployeeService employeeService;
    @Setter
    private IDepartmentService departmentService;
    @Setter
    private IRoleService roleService;
    @Getter
    private Employee employee = new Employee();
    @Getter
    private EmployeeQueryObject qo = new EmployeeQueryObject();

    @Override
    public String execute() throws Exception {
        //查询所有的部门信息,并放到context域中,作为下拉选择框的数据
        putContext("depts", departmentService.listAll());
        PageResult pageResult = employeeService.queryByCondition(qo);
        //数据放到域中.
        ActionContext.getContext().put("pageResult", pageResult);
        return LIST;
    }

    @RequiredPermission("员工编辑")
    @RequiresPermissions("employee:input")
    public String input() throws Exception {
        //查询所有的角色信息,并把放到context区域中.
        putContext("roles", roleService.listAll());

        //查询所有的部门信息,并放到context域中,作为下拉选择框的数据
        putContext("depts", departmentService.listAll());
        if (employee.getId() != null) {
            //此次请求是编辑的请求,查询对应的数据,并回显到表单中.
            employee = employeeService.get(employee.getId());
        }
        return "input";
    }

    @RequiredPermission("员工保存/更新")
    @RequiresPermissions("employee:saveOrUpdate")
    public String saveOrUpdate() throws Exception {

        if (employee.getId() != null) {
            //如果有ID是编辑
            employeeService.update(employee);
        } else {
            //如果没有ID是新增
            employeeService.save(employee);
        }
        return SUCCESS;
    }

    @RequiredPermission("员工删除")
    @RequiresPermissions("employee:delete")
    public String delete() throws Exception {
        employeeService.delete(employee.getId());
        return SUCCESS;
    }
}
