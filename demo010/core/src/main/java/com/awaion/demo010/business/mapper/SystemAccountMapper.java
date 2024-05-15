package com.awaion.demo010.business.mapper;

import com.awaion.demo010.business.domain.SystemAccount;

public interface SystemAccountMapper {

    int insert(SystemAccount record);

    SystemAccount selectCurrent();

    int updateByPrimaryKey(SystemAccount record);


}