package com.awaion.cloud.apis;

import com.awaion.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cloud-service1")
public interface CloudService1Api {
    @GetMapping(value = "/resilience4j/circuitbreaker/{id}")
    String myCircuit(@PathVariable("id") Integer id) throws InterruptedException;

    @GetMapping(value = "/resilience4j/bulkhead/{id}")
    String myBulkhead(@PathVariable("id") Integer id) throws InterruptedException;

    @GetMapping(value = "/resilience4j/ratelimiter/{id}")
    String myRatelimit(@PathVariable("id") Integer id);

    @GetMapping(value = "/feign/error/{id}")
    ResultData<Integer> error(@PathVariable("id") Integer id);

    @GetMapping(value = "/micrometer/pay/{id}")
    ResultData<String> myMicrometer(@PathVariable("id") Integer id);

}
