package com.awaion.demo010.base.query;


import com.awaion.demo010.base.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class UserFileQueryObject extends QueryObject {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private int state;

    private Long applierId;

    public Date getEndDate() {
        return endDate == null ? null : DateUtil.endOfDay(endDate);
    }


}
