package com.awaion.demo010.base.mapper;

import com.awaion.demo010.base.domain.VideoAuth;
import com.awaion.demo010.base.query.VideoAuthQueryObject;

import java.util.List;

public interface VideoAuthMapper {

    int insert(VideoAuth record);

    VideoAuth selectByPrimaryKey(Long id);

    int updateByPrimaryKey(VideoAuth record);

    //高级查询
    Integer querycount(VideoAuthQueryObject qo);

    List<VideoAuth> querydata(VideoAuthQueryObject qo);


}