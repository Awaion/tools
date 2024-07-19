package com.awaion.demo010.business.service;

import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.base.query.PlatFormBankInfoQueryObject;
import com.awaion.demo010.business.domain.PlatFormBankInfo;

import java.util.List;

public interface IPlatformBankInfoService {

    int insert(PlatFormBankInfo record);

    PlatFormBankInfo selectByPrimaryKey(Long id);

    List<PlatFormBankInfo> selectAll();

    int updateByPrimaryKey(PlatFormBankInfo record);


    PageResult advanQuery(PlatFormBankInfoQueryObject qo);


}
