package com.awaion.demo010.base.mapper;

import com.awaion.demo010.base.domain.VerifyEmailVO;

import java.util.List;

public interface VerifyEmailVOMapper {

    int insert(VerifyEmailVO record);

    List<VerifyEmailVO> selectAll();

    VerifyEmailVO selectByKey(String key);

}