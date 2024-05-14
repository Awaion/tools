package com.awaion.demo008.web.controller;

import com.awaion.demo008.page.AjaxResult;
import com.awaion.demo008.page.PageResult;
import com.awaion.demo008.query.PermissionQueryObject;
import com.awaion.demo008.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {
    @Autowired
    IPermissionService permissionService;

    @RequestMapping("")
    public String index() {
        return "permission";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(PermissionQueryObject qo) {
        PageResult pageResult = null;
        pageResult = permissionService.queryPagePage(qo);
        return pageResult;
    }

    @RequestMapping("/load")
    @ResponseBody
    public AjaxResult load() {
        AjaxResult result = null;
        try {
            permissionService.load();
            result = new AjaxResult(true, "加载权限成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("加载权限失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/queryPageDataForRoleForm")
    @ResponseBody
    public PageResult queryPageDataForRoleForm() {
        PageResult pageResult = null;
        pageResult = permissionService.queryPageForRoleForm();
        return pageResult;
    }

    @RequestMapping("/selectPageForRoleFormByRoleId")
    @ResponseBody
    public PageResult selectPageForRoleFormByRoleId(Long roleId) {
        PageResult pageResult = null;
        pageResult = permissionService.selectPageForRoleFormByRoleId(roleId);
        return pageResult;
    }

}
