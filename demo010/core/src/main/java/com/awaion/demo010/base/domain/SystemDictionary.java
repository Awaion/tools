package com.awaion.demo010.base.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class SystemDictionary extends BaseDomain {
    private String sn;
    private String title;


    public String getJsonString() {
        Map<String, Object> map = new HashMap<>();

        map.put("id", id);
        map.put("sn", sn);
        map.put("title", title);
        return JSON.toJSONString(map);
    }

}
