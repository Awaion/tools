package com.awaion.demo010.business.service.impl;

import com.awaion.demo010.base.domain.Account;
import com.awaion.demo010.base.domain.BidConsts;
import com.awaion.demo010.business.domain.AccountFlow;
import com.awaion.demo010.business.domain.Bid;
import com.awaion.demo010.business.domain.BidRequest;
import com.awaion.demo010.business.domain.RechargeOffline;
import com.awaion.demo010.business.mapper.AccountFlowMapper;
import com.awaion.demo010.business.service.IAccountFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class AccountFlowServiceImpl implements IAccountFlowService {

    @Autowired
    private AccountFlowMapper accountFlowMapper;

    @Override
    public void createRechargeOfflineFlow(Account account, RechargeOffline ro) {

        AccountFlow flow = new AccountFlow();
        flow.setUsableAmount(account.getUsableAmount());
        flow.setAccountId(account.getId());
        flow.setActionTime(new Date());
        flow.setActionType(BidConsts.ACCOUNT_ACTIONTYPE_DEPOSIT_OFFLINE_LOCAL); //--?
        flow.setAmount(ro.getAmount());
        flow.setFreezedAmount(account.getFreezedAmount());
        flow.setNote("线下充值成功,充值金额为:" + ro.getAmount());
        accountFlowMapper.insert(flow);

    }

    @Override
    public void createBidFlow(Account account, Bid bid) {
        AccountFlow flow = new AccountFlow();
        flow.setUsableAmount(account.getUsableAmount());
        flow.setAccountId(account.getId());
        flow.setActionTime(new Date());

        flow.setActionType(BidConsts.ACCOUNT_ACTIONTYPE_BID_FREEZED); //--?
        flow.setAmount(bid.getAvailableAmount());
        flow.setFreezedAmount(account.getFreezedAmount());
        flow.setNote(bid.getBidRequestTitle() + "投标成功,投标金额为:" + bid.getAvailableAmount());
        accountFlowMapper.insert(flow);
    }

    @Override
    public void createBidFailedFlow(Account bidAccount, Bid bid) {
        AccountFlow flow = createBaseFlow(bidAccount);

        flow.setActionType(BidConsts.ACCOUNT_ACTIONTYPE_BID_UNFREEZED); //--?
        flow.setAmount(bid.getAvailableAmount());
        flow.setFreezedAmount(bidAccount.getFreezedAmount());
        flow.setNote(bid.getBidRequestTitle() + "投标失败,取消冻结金额为:" + bid.getAvailableAmount());
        accountFlowMapper.insert(flow);
    }

    @Override
    public void createBorrowSuccessFlow(Account borrowAccount, BidRequest br) {

        AccountFlow flow = createBaseFlow(borrowAccount);

        flow.setActionType(BidConsts.ACCOUNT_ACTIONTYPE_BIDREQUEST_SUCCESSFUL); //-->表示成功借款
        flow.setAmount(br.getBidRequestAmount());

        /*flow.setFreezedAmount(br.get);*/
        flow.setNote(br.getTitle() + "二审成功,收款金额为:" + br.getBidRequestAmount());
        accountFlowMapper.insert(flow);

    }

    //支付手续费对应的生成的支付流水

    @Override
    public void createManageFeeFlow(Account borrowAccount, BidRequest br, BigDecimal manageFee) {

        AccountFlow flow = createBaseFlow(borrowAccount);

        flow.setActionType(BidConsts.ACCOUNT_ACTIONTYPE_CHARGE); //--?
        flow.setAmount(manageFee);
        /*flow.setFreezedAmount(borrowAccount.getFreezedAmount());*/

        flow.setNote(br.getTitle() + "支付借款手续费:" + manageFee);
        accountFlowMapper.insert(flow);


    }

    @Override
    public void createBidSuccessFlow(Account bidAccount, Bid bid) {

        AccountFlow flow = createBaseFlow(bidAccount);

        flow.setActionType(BidConsts.ACCOUNT_ACTIONTYPE_BID_SUCCESSFUL); //--?
        flow.setAmount(bid.getAvailableAmount());
        flow.setFreezedAmount(bidAccount.getFreezedAmount());
        flow.setNote(bid.getBidRequestTitle() + "投标成功,投标金额为:" + bid.getAvailableAmount());
        accountFlowMapper.insert(flow);

    }

    public AccountFlow createBaseFlow(Account account) {
        AccountFlow flow = new AccountFlow();
        flow.setUsableAmount(account.getUsableAmount());
        flow.setAccountId(account.getId());
        flow.setActionTime(new Date());
        return flow;
    }

}
