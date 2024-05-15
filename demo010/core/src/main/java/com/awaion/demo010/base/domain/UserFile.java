package com.awaion.demo010.base.domain;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

//分控材料认证对应的实体类

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFile extends BaseAuditDomain {

    private String image; //当前材料对应的图片-->存储文件的地址值

    private SystemDictionaryItem fileType;  //材料分类

    private int score;

    public String getJsonString() {

        Map<String, Object> map = new HashMap<>();

        map.put("id", id);
        map.put("state", state);
        map.put("applier", applier.getUsername());

        map.put("image", image);
        map.put("fileType", fileType.getTitle());

        return JSON.toJSONString(map);
    }

}
