package com.awaion.cloud.controller;

import com.awaion.cloud.entities.Order;
import com.awaion.cloud.resp.ResultData;
import com.awaion.cloud.serivce.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public ResultData create(Order order) {
        // http://localhost:10021/order/create?userId=1&productId=1&count=10&money=100
        orderService.create(order);
        return ResultData.success(order);
    }
}
