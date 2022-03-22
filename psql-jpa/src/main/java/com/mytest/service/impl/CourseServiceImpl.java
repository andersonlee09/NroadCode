package com.mytest.service.impl;

import com.mytest.dao.CourseDao;
import com.mytest.service.CourseService;
import com.mytest.utils.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/22 12:05
 * @purpose null
 * @ModifiedRecords null
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Autowired
    private RedisService redisService;


}
