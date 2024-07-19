package com.awaken.tool.demo003.controller;

import com.awaken.tool.demo003.model.ClassRoom;
import com.awaken.tool.demo003.model.Student;
import com.awaken.tool.demo003.model.Teacher;
import com.awaken.tool.demo003.service.ClassRoomService;
import com.awaken.tool.demo003.service.StudentService;
import com.awaken.tool.demo003.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/jpa")
@Tag(name = "JPA简单使用")
public class Demo003Controller {
    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassRoomService classRoomService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping(value = "/student/save", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "学生保存")
    public ResponseEntity<String> studentSave(@RequestBody @Validated Student student) {
        studentService.save(student);
        return ResponseEntity.ok("学生保存");
    }

    @PostMapping(value = "/teacher/save", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "教师保存")
    public ResponseEntity<String> teacherSave(@RequestBody @Validated Teacher teacher) {
        teacherService.save(teacher);
        return ResponseEntity.ok("教师保存");
    }

    @PostMapping(value = "/class/save", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "班级保存")
    public ResponseEntity<String> classSave(@RequestBody @Validated ClassRoom classRoom) {
        classRoomService.save(classRoom);
        return ResponseEntity.ok("班级保存");
    }

    @PostMapping(value = "/student/find1", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "学生查询1")
    public ResponseEntity<List<Student>> studentFind1(@RequestBody @Validated Student student) {
        List<Student> list = studentService.findByName(student.getName());
        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "/student/find2", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "学生查询2")
    public ResponseEntity<List<Student>> studentFind2(@RequestBody @Validated Student student) {
        List<Student> list = studentService.getByNameAndAge(student.getName(), student.getAge());
        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "/student/find3", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "学生查询3")
    public ResponseEntity<Long> studentFind3(@RequestBody @Validated Student student) {
        Long count = studentService.countByName(student.getName());
        return ResponseEntity.ok(count);
    }

    @PostMapping(value = "/teacher/find1", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "教师查询1")
    public ResponseEntity<List<Teacher>> teacherFind1(@RequestBody @Validated Teacher teacher) {
        List<Teacher> list = teacherService.getTeachers(teacher.getSubject());
        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "/teacher/find2", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "教师查询2")
    public ResponseEntity<Page<Teacher>> teacherFind2(@RequestBody @Validated Teacher teacher) {
        Page<Teacher> page = teacherService.getPage(teacher.getSubject());
        return ResponseEntity.ok(page);
    }
}
