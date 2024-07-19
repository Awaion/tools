package com.awaion.demo010.base.service.impl;

import com.awaion.demo010.base.domain.Logininfo;
import com.awaion.demo010.base.domain.UserInfo;
import com.awaion.demo010.base.domain.VideoAuth;
import com.awaion.demo010.base.mapper.VideoAuthMapper;
import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.base.query.VideoAuthQueryObject;
import com.awaion.demo010.base.service.ILogininfoService;
import com.awaion.demo010.base.service.IUserinfoService;
import com.awaion.demo010.base.service.IVideoAuthService;
import com.awaion.demo010.base.util.BitStatesUtils;
import com.awaion.demo010.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class VideoAuthServiceImpl implements IVideoAuthService {

    @Autowired
    private VideoAuthMapper videoAuthMapper;

    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private ILogininfoService logininfoService;


    @Override
    public int insert(VideoAuth record) {
        return videoAuthMapper.insert(record);
    }

    @Override
    public VideoAuth selectByPrimaryKey(Long id) {
        return videoAuthMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(VideoAuth record) {
        return videoAuthMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult advanQuery(VideoAuthQueryObject qo) {

        //查询总的记录条数
        Integer count = videoAuthMapper.querycount(qo);
        if (count == 0) {
            return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
        }
        //查询所有的对象形成的集合
        List<VideoAuth> data = videoAuthMapper.querydata(qo);

        return new PageResult(data, count, qo.getCurrentPage(), qo.getPageSize());

    }

    @Override
    public void vedioAuthAudit(int state, String remark, int loginInfoValue) {

        //判断用户是否通过视频认证
        UserInfo userInfo = userinfoService.selectByPrimaryKey((long) loginInfoValue);
        if (userInfo != null && !userInfo.getIsVedioAuth()) {  //用户不为null,同时用户目前还没有通过视频认证
            //创建一个视频认证对象
            VideoAuth va = new VideoAuth();
            //设置审核相关属性
            va.setAuditTime(new Date());
            va.setRemark(remark);
            va.setAuditor(UserContext.getCurrentUser());
            Logininfo apppliertest = logininfoService.selectByPrimaryKey((long) loginInfoValue);
            va.setApplier(apppliertest);

            va.setApplyTime(new Date());
            va.setState(state);

            if (state == VideoAuth.PASS) {
                //如果审核通过,为用户添加一个视频审核状态码
                userInfo.setBitState(BitStatesUtils.addState(userInfo.getBitState(), BitStatesUtils.OP_VEDIO_AUTH));
                userinfoService.updateByPrimaryKey(userInfo);//对应的更新userinfo对象
            }
            //保存
            videoAuthMapper.insert(va);
        }
    }
}
