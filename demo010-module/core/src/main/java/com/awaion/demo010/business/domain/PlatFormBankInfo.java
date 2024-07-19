package com.awaion.demo010.business.domain;

import com.alibaba.fastjson.JSON;
import com.awaion.demo010.base.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlatFormBankInfo extends BaseDomain {

    private String bankName;  //银行
    private String accountName; //开户人姓名
    private String accountNumber; //银行账号
    private String forkName;


    public String getJsonString() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("bankName", bankName);
        map.put("accountName", accountName);
        map.put("accountNumber", accountNumber);
        map.put("forkName", forkName);

        return JSON.toJSONString(map);
    }

}
