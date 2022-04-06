package com.test.api.facade.impl;

import com.test.api.facade.ProcessOrderServiceFacade;
import com.test.application.CountTotalPriceAfterDiscount;
import com.test.application.impl.CountTotalPriceAfterDiscountImpl;
import com.test.application.repository.OrderRepository;
import com.test.domain.Commodity;
import com.test.domain.Order;

import java.util.Map;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/4/6 12:57
 * @purpose null
 * @ModifiedRecords null
 */
public class ProcessOrderServiceFacadeImpl implements ProcessOrderServiceFacade {
    private final CountTotalPriceAfterDiscount countTotalPriceAfterDiscount= new CountTotalPriceAfterDiscountImpl();
    private final OrderRepository orderRepository = new OrderRepository();
    @Override
    public Order getProcessedOrder(Map<Integer, Commodity> commodityMap) {
        // 计算出订单
        Order order = this.countTotalPriceAfterDiscount.getCountTotalPriceAfterDiscount(commodityMap);
        // 保存订单
        orderRepository.save(order);
        return order;

    }
}
