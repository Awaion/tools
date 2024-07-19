package com.awaion.demo010.business.service;

import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.business.domain.RechargeOffline;
import com.awaion.demo010.business.query.RechargeOfflineQueryObject;

public interface IRechargeOfflineService {


    int apply(RechargeOffline record);

    RechargeOffline selectByPrimaryKey(Long id);

    int updateByPrimaryKey(RechargeOffline record);

    PageResult advanQuery(RechargeOfflineQueryObject qo);

    void audit(Long id, String remark, int state);
}
