package com.awaion.demo010.business.mapper;

import com.awaion.demo010.business.domain.PaymentScheduleDetail;

public interface PaymentScheduleDetailMapper {

    int insert(PaymentScheduleDetail record);

    PaymentScheduleDetail selectByPrimaryKey(Long id);


    int updateByPrimaryKey(PaymentScheduleDetail record);
}