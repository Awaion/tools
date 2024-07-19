package com.awaion.cloud.apis;

import com.awaion.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "alibaba-service1", fallback = AlibabaService1FeignApiFallBack.class)
public interface AlibabaService1FeignApi {
    @GetMapping(value = "/pay/nacos/get/{orderNo}")
    public ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo);
}
