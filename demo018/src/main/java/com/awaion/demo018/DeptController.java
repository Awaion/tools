package com.awaion.demo018;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "部门", description = "部门的CRUD")
@RestController
public class DeptController {
    @Autowired
    DeptService deptService;

    @Operation(summary = "查询", description = "按照id查询部门")
    @GetMapping("/dept/{id}")
    public Dept getDept(@PathVariable("id") Long id) {
        return deptService.getDeptById(id);
    }

    @Operation(summary = "查询所有部门")
    @GetMapping("/depts")
    public List<Dept> getDept() {
        return deptService.getDepts();
    }

    @Operation(summary = "保存部门", description = "必须提交json")
    @PostMapping("/dept")
    public String saveDept(@RequestBody Dept dept) {
        deptService.saveDept(dept);
        return "ok";
    }

    @Operation(summary = "按照id删除部门", description = "必须提交json")
    @DeleteMapping("/dept/{id}")
    public String deleteDept(@PathVariable("id") @Parameter(description = "部门id") Long id) {
        deptService.deleteDept(id);
        return "ok";
    }
}

