package com.awaion.demo010.business.mapper;

import com.awaion.demo010.business.domain.PaymentSchedule;

public interface PaymentScheduleMapper {

    int insert(PaymentSchedule record);

    PaymentSchedule selectByPrimaryKey(Long id);


    int updateByPrimaryKey(PaymentSchedule record);


}