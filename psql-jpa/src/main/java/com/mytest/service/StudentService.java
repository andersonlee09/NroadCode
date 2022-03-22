package com.mytest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mytest.domain.Student;

import java.util.List;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/22 11:47
 * @purpose null
 * @ModifiedRecords null
 */

public interface StudentService {

    Student queryOneStudentById(Integer id) throws JsonProcessingException;

    boolean updateOneStudent(Student student);

    List<Student> queryListStudent(Integer page, Integer limit);
}
