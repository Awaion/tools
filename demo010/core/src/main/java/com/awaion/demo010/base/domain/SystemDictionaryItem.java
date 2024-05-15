package com.awaion.demo010.base.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class SystemDictionaryItem extends BaseDomain {

    private Long parentId;  //关联数据字典分类

    private String title;

    private Integer sequence;


    public String getJsonString() {
        Map<String, Object> map = new HashMap<>();

        map.put("id", id);
        map.put("parentId", parentId);
        map.put("title", title);
        map.put("sequence", sequence);

        return JSON.toJSONString(map);
    }


}
