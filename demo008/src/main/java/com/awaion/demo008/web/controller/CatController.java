package com.awaion.demo008.web.controller;

import com.awaion.demo008.domain.Cat;
import com.awaion.demo008.page.AjaxResult;
import com.awaion.demo008.page.PageResult;
import com.awaion.demo008.query.CatQueryObject;
import com.awaion.demo008.service.ICatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cat")
public class CatController {
    @Autowired
    ICatService catService;

    @RequestMapping("")
    public String index() {
        return "cat";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(CatQueryObject qo) {
        PageResult pageResult = null;
        pageResult = catService.queryPage(qo);
        return pageResult;
    }

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Cat cat) {
        AjaxResult result = null;
        try {
            catService.insert(cat);
            result = new AjaxResult(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(Cat cat) {
        AjaxResult result = null;
        try {
            catService.updateByPrimaryKey(cat);
            result = new AjaxResult(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("更新失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long catId) {
        AjaxResult result = null;
        try {
            catService.deleteByPrimaryKey(catId);
            result = new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("删除失败,请联系管理员！");
        }
        return result;
    }
}
