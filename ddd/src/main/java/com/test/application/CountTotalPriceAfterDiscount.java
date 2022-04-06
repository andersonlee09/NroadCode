package com.test.application;

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
public interface CountTotalPriceAfterDiscount {
    Order getCountTotalPriceAfterDiscount(Map<Integer, Commodity> commodityMap);
}
