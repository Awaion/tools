package com.awaion.demo008.web.controller;

import com.awaion.demo008.domain.SystemLog;
import com.awaion.demo008.page.AjaxResult;
import com.awaion.demo008.page.PageResult;
import com.awaion.demo008.query.SystemLogQueryObject;
import com.awaion.demo008.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/systemLog")
public class SystemLogController {
    @Autowired
    ISystemLogService systemLogService;

    @RequestMapping("")
    public String index() {
        return "systemLog";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(SystemLogQueryObject qo) {
        PageResult pageResult = null;
        pageResult = systemLogService.queryPage(qo);
        return pageResult;
    }

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(SystemLog systemLog) {
        AjaxResult result = null;
        try {
            systemLogService.insert(systemLog);
            result = new AjaxResult(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(SystemLog systemLog) {
        AjaxResult result = null;
        try {
            systemLogService.updateByPrimaryKey(systemLog);
            result = new AjaxResult(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("更新失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long systemLogId) {
        AjaxResult result = null;
        try {
            systemLogService.deleteByPrimaryKey(systemLogId);
            result = new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("删除失败,请联系管理员！");
        }
        return result;
    }
}
