package com.mytest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mytest.domain.Student;
import com.mytest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/22 12:00
 * @purpose null
 * @ModifiedRecords null
 */
@RestController
@RequestMapping("/test")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/get/{id}")
    public Student getStudent(@PathVariable("id") Integer id) throws JsonProcessingException {
        return studentService.queryOneStudentById(id);
    }

    @GetMapping("/query")
    public Student queryOneStudent(@RequestParam(name = "id") Integer id) throws JsonProcessingException {
        return studentService.queryOneStudentById(id);
    }

    @PostMapping("/update")
    public void updateOneStudent(@RequestBody Student student) {
        studentService.updateOneStudent(student);
    }

    @GetMapping("/list")
    public List<Student> queryListStudent(@RequestParam(name = "page") Integer page, @RequestParam(name = "limit") Integer limit) {
        return studentService.queryListStudent(page, limit);
    }

}
