package com.mytest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytest.dao.StudentDao;
import com.mytest.domain.Student;
import com.mytest.service.StudentService;
import com.mytest.utils.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/22 12:06
 * @purpose null
 * @ModifiedRecords null
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private RedisService redisService;

    @Override
    public Student queryOneStudentById(Integer id) {
        Student student = redisService.getBean("student" + id, Student.class);
        if (student == null) {
            student = studentDao.getById(id);
            redisService.saveBean("student" + id, student);
        }
        return student;
    }

    @Override
    public boolean updateOneStudent(Student student) {
        return studentDao.save(student) == null;
    }

    @Override
    public List<Student> queryListStudent(Integer page, Integer limit) {

        String list = redisService.getString("list:page" + page + "-" + limit);
        if (list == null) {
            PageRequest pageable = PageRequest.of(page - 1, limit);
            List<Student> students = studentDao.findAll(pageable).getContent();
            redisService.saveString("list:page" + page + "-" + limit, JSON.toJSON(students).toString());
            return students;
        } else {
            return JSON.parseArray(list, Student.class);
        }
    }
}
