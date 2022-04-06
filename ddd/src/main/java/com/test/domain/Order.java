package com.test.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/4/6 12:40
 * @purpose null
 * @ModifiedRecords null
 */
@Data
public class Order {
    private Integer id;
    private Map<Integer, Commodity> map = new HashMap<>();
    // 总价格
    private Integer count;

    public int getCount() {
        AtomicInteger res = new AtomicInteger();
        map.forEach((k, v)-> res.addAndGet(v.cost));
        return res.get();
    }
}
