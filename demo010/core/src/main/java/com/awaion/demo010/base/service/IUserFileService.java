package com.awaion.demo010.base.service;

import com.awaion.demo010.base.domain.UserFile;
import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.base.query.UserFileQueryObject;

import java.util.List;

public interface IUserFileService {

    void createUserFile(String image);

    UserFile selectByPrimaryKey(Long id);

    int updateByPrimaryKey(UserFile userFile);

    List<UserFile> listUnselectTypeFiles(Long id);


    List<UserFile> listSelectTypeFiles(Long id);

    void userFileSelectType(Long[] id, Long[] fileType);

    PageResult advanQuery(UserFileQueryObject qo);

    //审核风控资料的方法
    void userFileAudit(Long id, String remark, int score, int state);

    List<UserFile> listPassedFiles(UserFileQueryObject qo);
}
