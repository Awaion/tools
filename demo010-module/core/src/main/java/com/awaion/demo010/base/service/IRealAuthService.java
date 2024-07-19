package com.awaion.demo010.base.service;

import com.awaion.demo010.base.domain.RealAuth;
import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.base.query.RealAuthQueryObject;

public interface IRealAuthService {

    int insert(RealAuth record);

    RealAuth selectByPrimaryKey(Long id);

    int updateByPrimaryKey(RealAuth record);

    void apply(RealAuth realAuth);

    PageResult advanQuery(RealAuthQueryObject qo);

    void realAuthAudit(Long id, String remark, int state);

}
