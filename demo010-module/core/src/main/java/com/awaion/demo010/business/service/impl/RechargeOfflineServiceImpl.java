package com.awaion.demo010.business.service.impl;

import com.awaion.demo010.base.domain.Account;
import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.base.service.IAccountService;
import com.awaion.demo010.base.util.UserContext;
import com.awaion.demo010.business.domain.RechargeOffline;
import com.awaion.demo010.business.mapper.RechargeOfflineMapper;
import com.awaion.demo010.business.query.RechargeOfflineQueryObject;
import com.awaion.demo010.business.service.IAccountFlowService;
import com.awaion.demo010.business.service.IRechargeOfflineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class RechargeOfflineServiceImpl implements IRechargeOfflineService {

    @Autowired
    private RechargeOfflineMapper rechargeOfflineMapper;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IAccountFlowService flowService;


    //提交充值
    @Override
    public int apply(RechargeOffline ro) {

        RechargeOffline newro = new RechargeOffline();

        newro.setApplier(UserContext.getCurrentUser());
        newro.setApplyTime(new Date());
        newro.setAmount(ro.getAmount());
        newro.setBankInfo(ro.getBankInfo());
        newro.setRechargeTime(ro.getRechargeTime());
        newro.setTradeCode(ro.getTradeCode());
        newro.setNote(ro.getNote());
        newro.setState(RechargeOffline.NORMAL);

        return rechargeOfflineMapper.insert(newro);
    }

    @Override
    public RechargeOffline selectByPrimaryKey(Long id) {
        return rechargeOfflineMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(RechargeOffline record) {
        return rechargeOfflineMapper.updateByPrimaryKey(record);
    }


    //高级查询充值记录
    @Override
    public PageResult advanQuery(RechargeOfflineQueryObject qo) {

        //查询总的记录条数
        Integer count = rechargeOfflineMapper.querycount(qo);

        if (count == 0) {
            return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
        }
        //查询所有的对象形成的集合
        List<RechargeOffline> data = rechargeOfflineMapper.querydata(qo);
        return new PageResult(data, count, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    public void audit(Long id, String remark, int state) {

        //得到充值对象
        RechargeOffline ro = rechargeOfflineMapper.selectByPrimaryKey(id);
        //设置充值对象的一些属性
        ro.setAuditTime(new Date());
        ro.setAuditor(UserContext.getCurrentUser());
        ro.setRemark(remark);
        ro.setState(state);

        if (state == RechargeOffline.PASS) {//如果审核通过
            //得到申请人的账户,添加账户余额
            Account account = accountService.selectByPrimaryKey(ro.getApplier().getId());
            account.setUsableAmount(account.getUsableAmount().add(ro.getAmount()));
            //创建充值流水
            flowService.createRechargeOfflineFlow(account, ro);

            accountService.updateByPrimaryKey(account);
        }

        //更新充值对象
        rechargeOfflineMapper.updateByPrimaryKey(ro);


    }
}
