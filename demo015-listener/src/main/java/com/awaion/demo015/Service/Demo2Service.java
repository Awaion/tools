package com.awaion.demo015.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Demo2Service {

    public void biz02(String string) {
        log.info("===业务2===Demo1Service:biz02==={}", string);
    }
}
