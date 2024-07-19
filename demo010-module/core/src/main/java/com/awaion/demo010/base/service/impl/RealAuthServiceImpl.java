package com.awaion.demo010.base.service.impl;

import com.awaion.demo010.base.domain.RealAuth;
import com.awaion.demo010.base.domain.UserInfo;
import com.awaion.demo010.base.mapper.RealauthMapper;
import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.base.query.RealAuthQueryObject;
import com.awaion.demo010.base.service.ILogininfoService;
import com.awaion.demo010.base.service.IRealAuthService;
import com.awaion.demo010.base.service.IUserinfoService;
import com.awaion.demo010.base.util.BitStatesUtils;
import com.awaion.demo010.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class RealAuthServiceImpl implements IRealAuthService {

    @Autowired
    private RealauthMapper realauthMapper;
    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private ILogininfoService logininfoService;


    //审核列表对应的高级功能
    @Override
    public PageResult advanQuery(RealAuthQueryObject qo) {


        //查询总的记录条数
        Integer count = realauthMapper.querycount(qo);
        if (count == 0) {
            return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
        }
        //查询所有的对象形成的集合
        List<RealAuth> data = realauthMapper.querydata(qo);

        return new PageResult(data, count, qo.getCurrentPage(), qo.getPageSize());

    }


    //实名认证的审核方法
    @Override
    public void realAuthAudit(Long id, String remark, int state) {


        //判断用户处于未审核的状态
        RealAuth ra = selectByPrimaryKey(id);
        //前台用户
        UserInfo applieruserinfo = userinfoService.selectByPrimaryKey(ra.getApplier().getId());

        if (ra != null) {

            ////1,实名认证对象处于待审核状态+这个用户没有实名认证
            if (applieruserinfo.getRealAuthId() != null && !(applieruserinfo.getIsRealAuth())) {
                //2,设值审核相关信息
                ra.setAuditor((UserContext.getCurrentUser()));
                ra.setAuditTime(new Date());
                ra.setRemark(remark);
                ra.setState(state);
                realauthMapper.updateByPrimaryKey(ra);

                //3,如果审核通过
                if (RealAuth.PASS == state) {

                    // 3--1,修改用户状态码
                    applieruserinfo.setBitState(BitStatesUtils.addState(applieruserinfo.getBitState(), BitStatesUtils.OP_REAL_AUTH));
                    //3 -- 2,设置用户冗余数据

                    applieruserinfo.setRealName(ra.getRealName());
                    applieruserinfo.setIdNumber(ra.getIdNumber());

                } else {  //审核不通过
                    applieruserinfo.setRealAuthId(null);
                }
                userinfoService.updateByPrimaryKey(applieruserinfo);
            }
        }


    }


    //对认证进行提交申请的方法
    @Override
    public void apply(RealAuth vo) {
        //获取当前用户
        UserInfo currentUserinfo = userinfoService.selectByPrimaryKey(UserContext.getCurrentUser().getId());
        //当前用户没有通过认证+realAuthId为空

        if (!currentUserinfo.getIsRealAuth() && currentUserinfo.getRealAuthId() == null) {
            //创建一个新的认证对象,将所有的信息设置给它

            RealAuth ra = new RealAuth();
            ra.setAddress(vo.getAddress());
            ra.setApplier(UserContext.getCurrentUser());
            ra.setApplyTime(new Date());
            ra.setBirthday(vo.getBirthday());
            ra.setIdNumber(vo.getIdNumber());
            ra.setImage1(vo.getImage1());
            ra.setImage2(vo.getImage2());
            ra.setRealName(vo.getRealName());
            ra.setSex(vo.getSex());
            ra.setState(RealAuth.NORMAL);

            //保存认证对象
            realauthMapper.insert(ra);

            //将保存之后的realathid设置给userinfo
            currentUserinfo.setRealAuthId(ra.getId());
            //更新userinfo
            userinfoService.updateByPrimaryKey(currentUserinfo);
        }
    }


    @Override
    public int insert(RealAuth record) {
        return realauthMapper.insert(record);

    }

    @Override
    public RealAuth selectByPrimaryKey(Long id) {
        return realauthMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(RealAuth record) {

        return realauthMapper.updateByPrimaryKey(record);
    }


}
