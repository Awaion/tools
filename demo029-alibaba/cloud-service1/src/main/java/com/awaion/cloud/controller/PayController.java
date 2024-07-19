package com.awaion.cloud.controller;

import com.awaion.cloud.entities.Pay;
import com.awaion.cloud.entities.PayDTO;
import com.awaion.cloud.resp.ResultData;
import com.awaion.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Tag(name = "支付微服务模块", description = "CRUD")
public class PayController {
    @Resource
    private PayService payService;
    @Value("${server.port}")
    private String port;

    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增", description = "新增支付流水方法,json串做参数")
    public ResultData<String> addPay(@RequestBody Pay pay) {
        // http://localhost:10001/pay/add {"payNo":"payNo001","orderNo":"orderNo001","userId":1,"amount":"6.66"}
        return ResultData.success(String.valueOf(payService.add(pay)));
    }

    @DeleteMapping(value = "/pay/del/{id}")
    @Operation(summary = "删除", description = "删除支付流水方法")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        // http://localhost:10001/pay/del/3
        return ResultData.success(payService.delete(id));
    }

    @PutMapping(value = "/pay/update")
    @Operation(summary = "修改", description = "修改支付流水方法")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
        // http://localhost:10001/pay/update {"id":2,"payNo":"payNo001","orderNo":"orderNo001","userId":1,"amount":"6.66"}
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        return ResultData.success(String.valueOf(payService.update(pay)));
    }

    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "按照ID查流水", description = "查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        // http://localhost:10001/pay/get/2
        return ResultData.success(payService.getById(id).setPort(port));
    }

}
