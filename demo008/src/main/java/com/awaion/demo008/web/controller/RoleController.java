package com.awaion.demo008.web.controller;

import com.awaion.demo008.domain.Role;
import com.awaion.demo008.page.AjaxResult;
import com.awaion.demo008.page.PageResult;
import com.awaion.demo008.query.RoleQueryObject;
import com.awaion.demo008.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    IRoleService roleService;

    @RequestMapping("")
    public String index() {
        return "role";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(RoleQueryObject qo) {
        PageResult pageResult = null;
        pageResult = roleService.queryPage(qo);
        return pageResult;
    }

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Role role) {
        AjaxResult result = null;
        try {
            roleService.insert(role);
            result = new AjaxResult(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(Role role) {
        AjaxResult result = null;
        try {
            roleService.updateByPrimaryKey(role);
            result = new AjaxResult(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("更新失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long roleId) {
        AjaxResult result = null;
        try {
            roleService.deleteByPrimaryKey(roleId);
            result = new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("删除失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/selectListForEmployeeForm")
    @ResponseBody
    public List<Role> selectListForEmployeeForm() {
        List<Role> result = roleService.selectAll();
        return result;
    }

    @RequestMapping("/queryRoleIdListForEmployeeForm")
    @ResponseBody
    public List<Long> queryRoleIdListForEmployeeForm(Long employeeId) {
        List<Long> result = roleService.queryRoleIdListForEmployeeForm(employeeId);
        return result;
    }

    @RequestMapping("/addMenu")
    @ResponseBody
    public AjaxResult addMenu(@RequestParam(value = "ids[]", required = false) ArrayList<Long> ids, Long roleId) {
        AjaxResult result = null;
        try {
            if (ids == null) {
                ids = new ArrayList<Long>();
            }
            roleService.addMenu(ids, roleId);
            result = new AjaxResult(true, "添加菜单成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("添加菜单失败,请联系管理员！");
        }
        return result;
    }
}
