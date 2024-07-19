package com.awaion.demo010.business.service;

import com.awaion.demo010.base.domain.Account;
import com.awaion.demo010.business.domain.Bid;
import com.awaion.demo010.business.domain.BidRequest;
import com.awaion.demo010.business.domain.RechargeOffline;

import java.math.BigDecimal;

public interface IAccountFlowService {

    void createRechargeOfflineFlow(Account account, RechargeOffline ro);

    void createBidFlow(Account account, Bid bid);

    void createBidFailedFlow(Account bidAccount, Bid bid);

    void createBorrowSuccessFlow(Account borrowAccount, BidRequest br);

    void createManageFeeFlow(Account borrowAccount, BidRequest br, BigDecimal manageFee);

    void createBidSuccessFlow(Account bidAccount, Bid bid);
}
