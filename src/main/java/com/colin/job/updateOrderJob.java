package com.colin.job;

import com.colin.entity.OrderHeadVo;
import com.colin.entity.OrderVo;
import com.colin.service.DeleteService;
import com.colin.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class description
 * @version        18/08/30
 * @author         Colin
 */
@Component
public class updateOrderJob {

    @Autowired
    private OrderService orderService;

    /**
     * 定时去JDE查询订单是否审批完成
     */
    @Scheduled(cron = "0 0/15 * * * ?")
    public void execute() {
        List<OrderHeadVo> orderHeadVos = orderService.findPendingOrders();
        if (orderHeadVos != null && orderHeadVos.size() > 0){
            for (int i = 0; i < orderHeadVos.size(); i++) {
                OrderHeadVo orderHeadVo = orderHeadVos.get(i);
                Long orderNumber = orderHeadVo.getOrderNumber();
                String orderCreator = orderHeadVo.getOrderCreator();
                List<OrderVo> orderDetails = orderService.findOrderDetails(orderNumber, orderCreator);
                if (orderDetails != null && orderDetails.size() > 0){
                    int count = 0;
                    for (int j = 0; j < orderDetails.size(); j++) {
                        OrderVo orderVo = orderDetails.get(j);
                        String tmedsp = orderVo.getTmedsp();
                        if(tmedsp.equals("Y")){
                            orderService.updateOrder(orderVo);
                            break;
                        }
                        if(tmedsp.equals("S")){
                            count++;
                            if(count == orderDetails.size()){
                                orderService.updateOrder(orderVo);
                            }
                        }
                    }
                }
            }
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
