package com.mytest.service.impl;

import com.mytest.dao.SCDao;
import com.mytest.service.SCService;
import com.mytest.utils.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/22 12:06
 * @purpose null
 * @ModifiedRecords null
 */
@Service
public class SCServiceImpl implements SCService {
    @Autowired
    private SCDao scDao;

    @Autowired
    private RedisService redisService;
}
