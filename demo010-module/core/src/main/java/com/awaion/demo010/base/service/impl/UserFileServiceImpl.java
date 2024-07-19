package com.awaion.demo010.base.service.impl;

import com.awaion.demo010.base.domain.SystemDictionaryItem;
import com.awaion.demo010.base.domain.UserFile;
import com.awaion.demo010.base.domain.UserInfo;
import com.awaion.demo010.base.mapper.UserFileMapper;
import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.base.query.UserFileQueryObject;
import com.awaion.demo010.base.service.IUserFileService;
import com.awaion.demo010.base.service.IUserinfoService;
import com.awaion.demo010.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class UserFileServiceImpl implements IUserFileService {

    @Autowired
    private UserFileMapper userFileMapper;

    @Autowired
    private IUserinfoService userinfoService;

    @Override
    public void userFileAudit(Long id, String remark, int score, int state) {

        //获取审核对象
        UserFile uf = selectByPrimaryKey(id);

        if (uf != null && uf.getState() == UserFile.NORMAL) {
            //设置公共的审核参数

            uf.setAuditTime(new Date());
            uf.setAuditor(UserContext.getCurrentUser());
            uf.setRemark(remark);
            uf.setState(state);
            if (state == UserFile.PASS) {
                uf.setScore(score);  //给当前资料设置分数

                //将分数累加到userinfo上
                UserInfo userInfo = userinfoService.selectByPrimaryKey(uf.getApplier().getId());
                userInfo.setScore(userInfo.getScore() + score);
                userinfoService.updateByPrimaryKey(userInfo);  //更新userinfo--->分数
            }

            userFileMapper.updateByPrimaryKey(uf);  //审核通过+不通过 都要更新资料的状态(保存备注等信息)
        }
    }

    @Override
    public List<UserFile> listPassedFiles(UserFileQueryObject qo) {
        return userFileMapper.querydata(qo);
    }


    @Override
    public List<UserFile> listUnselectTypeFiles(Long userId) {
        return userFileMapper.listUnselectTypeFiles(userId);
    }

    @Override
    public List<UserFile> listSelectTypeFiles(Long userId) {
        return userFileMapper.listSelectTypeFiles(userId);
    }

    @Override
    public void userFileSelectType(Long[] ids, Long[] fileTypes) {

        for (int i = 0; i < ids.length; i++) {

            UserFile userFile = userFileMapper.selectByPrimaryKey(ids[i]);

            SystemDictionaryItem item = new SystemDictionaryItem();

            item.setId(fileTypes[i]);  //创建字典对象--->文件类型

            userFile.setFileType(item);
            userFileMapper.updateByPrimaryKey(userFile);
        }
    }


    @Override
    public PageResult advanQuery(UserFileQueryObject qo) {
        //查询总的记录条数
        Integer count = userFileMapper.querycount(qo);
        if (count == 0) {
            return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
        }
        //查询所有的对象形成的集合
        List<UserFile> data = userFileMapper.querydata(qo);

        return new PageResult(data, count, qo.getCurrentPage(), qo.getPageSize());
    }


    @Override
    public void createUserFile(String image) {

        UserFile userFile = new UserFile();
        userFile.setApplier(UserContext.getCurrentUser());
        userFile.setApplyTime(new Date());
        userFile.setImage(image);
        userFileMapper.insert(userFile);
    }


    @Override
    public UserFile selectByPrimaryKey(Long id) {
        return userFileMapper.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKey(UserFile userFile) {
        return userFileMapper.updateByPrimaryKey(userFile);
    }


}
