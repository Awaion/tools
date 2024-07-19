package com.awaion.demo010.base.mapper;

import com.awaion.demo010.base.domain.UserFile;
import com.awaion.demo010.base.query.UserFileQueryObject;

import java.util.List;

public interface UserFileMapper {

    int insert(UserFile record);

    UserFile selectByPrimaryKey(Long id);

    int updateByPrimaryKey(UserFile record);


    //选择已经/没有选择类型的所有审核资料
    List<UserFile> listUnselectTypeFiles(Long userId);

    List<UserFile> listSelectTypeFiles(Long userId);


    //高级查询对应方法
    Integer querycount(UserFileQueryObject qo);

    List<UserFile> querydata(UserFileQueryObject qo);
}