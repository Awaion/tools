package com.awaion.cloud.controller;

import com.awaion.cloud.resp.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MicrometerController {
    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/micrometer/pay/{id}")
    public ResultData<String> myMicrometer(@PathVariable("id") Integer id) {
        // http://localhost:10001/micrometer/pay/1 Micrometer(Sleuth) 链路监控例子
        log.info("Micrometer id:{},port:{}", id, port);
        return ResultData.success(id + "," + port);
    }
}
