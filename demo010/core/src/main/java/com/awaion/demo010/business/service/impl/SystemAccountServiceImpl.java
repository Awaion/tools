package com.awaion.demo010.business.service.impl;

import com.awaion.demo010.base.domain.BidConsts;
import com.awaion.demo010.business.domain.BidRequest;
import com.awaion.demo010.business.domain.SystemAccount;
import com.awaion.demo010.business.domain.SystemAccountFlow;
import com.awaion.demo010.business.mapper.SystemAccountFlowMapper;
import com.awaion.demo010.business.mapper.SystemAccountMapper;
import com.awaion.demo010.business.service.ISystemAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class SystemAccountServiceImpl implements ISystemAccountService {

    @Autowired
    private SystemAccountMapper systemAccountMapper;


    @Autowired
    private SystemAccountFlowMapper flowMapper;

    @Override
    public void chargeManageFee(BigDecimal manageFee, BidRequest br) {

        //获取系统账户
        SystemAccount account = systemAccountMapper.selectCurrent();

        //增加可用余额
        if (account != null) {
            account.setUsableAmount(account.getUsableAmount().add(manageFee));
        } else {
            account = new SystemAccount();
            account.setUsableAmount(manageFee);
        }


        //生成流水
        SystemAccountFlow flow = new SystemAccountFlow();
        flow.setActionTime(new Date());
        flow.setActionType(BidConsts.SYSTEM_ACCOUNT_ACTIONTYPE_MANAGE_CHARGE);

        flow.setAmount(manageFee);
        flow.setFreezedAmount(account.getFreezedAmount());
        flow.setNote("借款:" + br.getTitle() + "成功,收取手续费:" + manageFee);

        flow.setUsableAmount(account.getUsableAmount());
        this.updateByPrimaryKey(account);

        flowMapper.insert(flow);
    }


    @Override
    public int insert(SystemAccount record) {
        return systemAccountMapper.insert(record);
    }

    @Override
    public SystemAccount selectCurrent() {
        return systemAccountMapper.selectCurrent();
    }

    @Override
    public int updateByPrimaryKey(SystemAccount record) {
        int ret = systemAccountMapper.updateByPrimaryKey(record);
        if (ret <= 0) {
            throw new RuntimeException("系统账户对象乐观锁失败");
        }
        return ret;
    }
}
