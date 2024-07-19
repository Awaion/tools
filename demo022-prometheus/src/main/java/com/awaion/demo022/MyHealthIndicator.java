package com.awaion.demo022;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class MyHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        // health 节点 新增监控信息
        int check = myHealthCheck();
        if (check == 1) {
            builder.up()
                    .withDetail("code", "1000")
                    .withDetail("msg", "健康")
                    .withDetail("data", "吃得好睡得香")
                    .build();
        } else {
            builder.down()
                    .withDetail("code", "1001")
                    .withDetail("msg", "不健康")
                    .withDetail("data", "吃不好睡不香")
                    .build();
        }

    }

    public int myHealthCheck() {
        // 监控信息业务处理
        return 1;
    }
}

