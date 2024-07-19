package com.awaion.demo010.business.mapper;

import com.awaion.demo010.base.query.PlatFormBankInfoQueryObject;
import com.awaion.demo010.business.domain.PlatFormBankInfo;

import java.util.List;

public interface PlatFormBankInfoMapper {

    int insert(PlatFormBankInfo record);

    PlatFormBankInfo selectByPrimaryKey(Long id);

    List<PlatFormBankInfo> selectAll();

    int updateByPrimaryKey(PlatFormBankInfo record);

    //高级查询
    Integer querycount(PlatFormBankInfoQueryObject qo);

    List<PlatFormBankInfo> querydata(PlatFormBankInfoQueryObject qo);


}