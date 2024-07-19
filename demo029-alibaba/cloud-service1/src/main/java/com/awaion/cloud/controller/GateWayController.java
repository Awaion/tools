package com.awaion.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.awaion.cloud.resp.ResultData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

@RestController
public class GateWayController {
    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/gateway/pay/get/{id}")
    public ResultData<Integer> getById(@PathVariable("id") Integer id) {
        // http://localhost:10001/gateway/pay/get/1
        return ResultData.success(id);
    }

    @GetMapping(value = "/gateway/pay/info")
    public ResultData<String> getGatewayInfo() {
        // http://localhost:10001/gateway/pay/info
        return ResultData.success(IdUtil.simpleUUID() + "," + port);
    }

    @GetMapping(value = "/gateway/pay/filter")
    public ResultData<String> getGatewayFilter(HttpServletRequest request) {
        // http://localhost:10001/gateway/pay/filter
        StringBuilder result = new StringBuilder();
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headName = headers.nextElement();
            String headValue = request.getHeader(headName);
            System.out.println("headName: " + headName + ",headValue: " + headValue);
            if (headName.equalsIgnoreCase("X-Request-awaion1")
                    || headName.equalsIgnoreCase("X-Request-awaion2")) {
                result.append("headName: ").append(headName).append(",headValue: ").append(headValue).append("\n");
            }
        }
        System.out.println("=============================================");
        String customerId = request.getParameter("customerId");
        System.out.println("customerId: " + customerId);

        String customerName = request.getParameter("customerName");
        System.out.println("customerName: " + customerName);
        System.out.println("=============================================");

        return ResultData.success(result.toString());
    }
}