// @author Tjzlo
// @version 2025/3/22 00:36

package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

//    @Scheduled(cron = "1/10 * * * * *")
    @Scheduled(cron = "0 * * * * *")
    public void processTimeoutOrder() {
        log.info("定时处理超时订单:{}", LocalDateTime.now());
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, LocalDateTime.now().minusMinutes(15));
        
        if (ordersList != null && ordersList.size() > 0) {
            for (Orders orders : ordersList) {
                orders.builder()
                        .status(Orders.CANCELLED)
                        .cancelReason("订单超时，已取消")
                        .cancelTime(LocalDateTime.now())
                        .build();
                orderMapper.update(orders);
            }
        }
    }

    @Scheduled(cron = "0 0 1 * * *")
//    @Scheduled(cron = "3/10 * * * * *")
    public void processDeliveryOrder() {
        log.info("定时处理处于派送中状态的订单:{}", LocalDateTime.now());
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, LocalDateTime.now().minusMinutes(60));

        if (ordersList != null && ordersList.size() > 0) {
            for (Orders orders : ordersList) {
                orders.builder()
                        .status(Orders.COMPLETED)
                        .build();
                orderMapper.update(orders);
            }
        }
    }
}
