package com.test.application.impl;

import com.test.application.CountTotalPriceAfterDiscount;
import com.test.domain.Commodity;
import com.test.domain.Order;

import java.util.Map;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/4/6 13:01
 * @purpose null
 * @ModifiedRecords null
 */
public class CountTotalPriceAfterDiscountImpl implements CountTotalPriceAfterDiscount {
    @Override
    public Order getCountTotalPriceAfterDiscount(Map<Integer, Commodity> commodityMap) {
        Order order = new Order();
        order.setMap(commodityMap);
        double countByCount = order.getCount();
        double countBySum = order.getCount();
        if (commodityMap.size() > 5) {
            countByCount = order.getCount() * 0.8;
        }
        if (order.getCount() > 500) {
            countBySum = order.getCount() * 0.7;
        }
        order.setCount((int) Math.min(countByCount, countBySum));
        return order;
    }
}
