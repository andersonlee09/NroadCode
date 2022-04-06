package com.test.api.facade;

import com.test.domain.Commodity;
import com.test.domain.Order;

import java.util.Map;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/4/6 12:56
 * @purpose null
 * @ModifiedRecords null
 */
public interface ProcessOrderServiceFacade {
    public Order getProcessedOrder(Map<Integer, Commodity> commodityMap);
}
