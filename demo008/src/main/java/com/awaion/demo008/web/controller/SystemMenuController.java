package com.awaion.demo008.web.controller;

import com.awaion.demo008.domain.SystemMenu;
import com.awaion.demo008.page.AjaxResult;
import com.awaion.demo008.page.PageResult;
import com.awaion.demo008.query.SystemMenuQueryObject;
import com.awaion.demo008.service.ISystemMenuService;
import com.awaion.demo008.util.MenuUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/systemMenu")
public class SystemMenuController extends BaseController {
    @Autowired
    ISystemMenuService systemMenuService;

    @RequestMapping("")
    public String index() {
        return "systemMenu";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(SystemMenuQueryObject qo) {
        PageResult pageResult = null;
        pageResult = systemMenuService.queryPage(qo);
        return pageResult;
    }

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(SystemMenu systemMenu) {
        AjaxResult result = null;
        try {
            systemMenuService.insert(systemMenu);
            result = new AjaxResult(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(SystemMenu systemMenu) {
        AjaxResult result = null;
        try {
            systemMenuService.updateByPrimaryKey(systemMenu);
            result = new AjaxResult(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("更新失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long systemMenuId) {
        AjaxResult result = null;
        try {
            systemMenuService.deleteByPrimaryKey(systemMenuId);
            result = new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("删除失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/queryTree")
    @ResponseBody
    public List<SystemMenu> queryTree() {
        List<SystemMenu> result = systemMenuService.queryTree();
        return result;
    }

    @RequestMapping("/queryForRole")
    @ResponseBody
    public List queryForRole() {
        List<SystemMenu> result = systemMenuService.queryForRole();
        return result;
    }

    @RequestMapping("/queryMenuIdsListForRole")
    @ResponseBody
    public List<Long> queryMenuIdsListForRole(Long roleId) {
        List<Long> result = systemMenuService.queryMenuIdsListForRole(roleId);
        return result;
    }

    @RequestMapping("/indexMenu")
    @ResponseBody
    public List<SystemMenu> indexMenu() {

        final List<SystemMenu> result = (List<SystemMenu>) SecurityUtils.getSubject().getSession().getAttribute(MenuUtil.USERMENU);

        /*List<SystemMenu> result = systemMenuService.indexMenu();*/
        return result;


    }
}
