package com.mytest.controller;

import com.mytest.service.CourseService;
import com.mytest.service.SCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/22 12:04
 * @purpose null
 * @ModifiedRecords null
 */
@RestController
public class SCController {
    @Autowired
    private SCService scService;
}
