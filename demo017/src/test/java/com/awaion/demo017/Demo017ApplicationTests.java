package com.awaion.demo017;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

@SpringBootTest
class Demo017ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    void redisTest() {
        redisTemplate.opsForValue().set("a", "1234");
        Assertions.assertEquals("1234", redisTemplate.opsForValue().get("a"));
    }

    @Test
    void testList() {
        String listName = "listtest";
        redisTemplate.opsForList().leftPush(listName, "1");
        redisTemplate.opsForList().leftPush(listName, "2");
        redisTemplate.opsForList().leftPush(listName, "3");
        String pop = redisTemplate.opsForList().leftPop(listName);
        Assertions.assertEquals("3", pop);
    }

    @Test
    void testSet() {
        String setName = "settest";
        redisTemplate.opsForSet().add(setName, "1", "2", "3", "3");
        Boolean member = redisTemplate.opsForSet().isMember(setName, "2");
        Assertions.assertTrue(member);
        Boolean member1 = redisTemplate.opsForSet().isMember(setName, "5");
        Assertions.assertFalse(member1);
    }

    @Test
    void testzset() {
        String setName = "zsettest";
        redisTemplate.opsForZSet().add(setName, "张三", 99.00);
        redisTemplate.opsForZSet().add(setName, "李四", 9.00);
        redisTemplate.opsForZSet().add(setName, "王五", 97.10);
        ZSetOperations.TypedTuple<String> popMax = redisTemplate.opsForZSet().popMax(setName);
        String value = popMax.getValue();
        Double score = popMax.getScore();
        System.out.println(value + "==>" + score);
    }

    @Test
    void testhash() {
        String mapName = "amap";
        redisTemplate.opsForHash().put(mapName, "name", "张三");
        redisTemplate.opsForHash().put(mapName, "age", "18");
        System.out.println(redisTemplate.opsForHash().get(mapName, "name"));
    }

}
