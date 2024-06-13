package com.awaion.demo015.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Demo1Service {

    public void biz01(String string) {
        log.info("===业务1===Demo1Service:biz01==={}", string);
    }
}
