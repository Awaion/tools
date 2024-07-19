package com.awaion.demo010.base.query;


import com.awaion.demo010.base.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class IpLogQueryObject extends QueryObject {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String username;

    private int state = -1; //登录状态

    private int userType = -1;  //用户类型,前台/后台管理员


    public Date getEndDate() {
        return endDate == null ? null : DateUtil.endOfDay(endDate);
    }


}
