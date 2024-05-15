package com.awaion.demo010.business.service;

import com.awaion.demo010.business.domain.BidRequest;
import com.awaion.demo010.business.domain.SystemAccount;

import java.math.BigDecimal;

public interface ISystemAccountService {

    int insert(SystemAccount record);

    SystemAccount selectCurrent();

    int updateByPrimaryKey(SystemAccount record);

    void chargeManageFee(BigDecimal manageFee, BidRequest br);
}
