package com.awaion.demo015.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Demo3Service {

    public void biz03(String string) {
        log.info("===业务3===Demo1Service:biz03==={}", string);
    }
}
