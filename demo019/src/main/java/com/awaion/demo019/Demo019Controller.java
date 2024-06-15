package com.awaion.demo019;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Demo019Controller {

    @GetMapping("/get-api")
    public Map<String, Object> getApi(@RequestParam("param") String param) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "请求成功");
        result.put("data", new String[]{"A", "B", "C", param});
        return result;
    }

    @PostMapping("/post-api")
    public Map<String, Object> postApi(@RequestBody Map<String, Object> body) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "请求成功");
        result.put("data", body);
        return result;
    }
}

