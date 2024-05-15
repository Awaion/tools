package com.awaion.demo010.base.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class RealAuth extends BaseAuditDomain {


    public static final int MALE = 0;
    public static final int FEMALE = 1;


    //------------------------------------

    private String realName;
    private int sex;

    private String idNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String address;

    private String image1;

    private String image2;


    public String getJsonString() {


        SimpleDateFormat formator = new SimpleDateFormat("yyyy-MM-dd");


        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("state", state);
        map.put("username", applier.getUsername());
        map.put("realName", realName);
        map.put("idNumber", idNumber);
        map.put("sex", getSexDisplay());
        map.put("birthday", formator.format(birthday));
        map.put("address", address);
        map.put("image1", image1);
        map.put("image2", image2);
        return JSON.toJSONString(map);
    }


    public String getSexDisplay() {
        return sex == MALE ? "男" : "女";
    }

}
