package com.awaion.demo010.base.service;

import com.awaion.demo010.base.domain.VideoAuth;
import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.base.query.VideoAuthQueryObject;

public interface IVideoAuthService {

    int insert(VideoAuth record);

    VideoAuth selectByPrimaryKey(Long id);

    int updateByPrimaryKey(VideoAuth record);

    //高级查询对应功能
    PageResult advanQuery(VideoAuthQueryObject qo);

    void vedioAuthAudit(int state, String remark, int loginInfoValue);
}
