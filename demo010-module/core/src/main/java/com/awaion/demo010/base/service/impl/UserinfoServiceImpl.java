package com.awaion.demo010.base.service.impl;

import com.awaion.demo010.base.domain.Consts;
import com.awaion.demo010.base.domain.UserInfo;
import com.awaion.demo010.base.domain.VerifyCodeVO;
import com.awaion.demo010.base.domain.VerifyEmailVO;
import com.awaion.demo010.base.mapper.UserinfoMapper;
import com.awaion.demo010.base.mapper.VerifyEmailVOMapper;
import com.awaion.demo010.base.service.IUserinfoService;
import com.awaion.demo010.base.util.BitStatesUtils;
import com.awaion.demo010.base.util.DateUtil;
import com.awaion.demo010.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserinfoServiceImpl implements IUserinfoService {

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Autowired
    private VerifyEmailVOMapper verifyEmailVOMapper;


    @Override
    public boolean bindEmail(String key) {
        //根据key得到emailvo对象--->必须要有

        VerifyEmailVO mv = verifyEmailVOMapper.selectByKey(key);
        if (mv != null) {
            UserInfo currentUserinfo = this.selectByPrimaryKey(UserContext.getCurrentUser().getId());
            if (!currentUserinfo.getHasBindEmail()  //没有绑定邮箱
                    && DateUtil.getIntervalSeconds(new Date(), mv.getSendTime()) < Consts.VERIFYEMAIL_VAILID_DAY * 24 * 3600   //邮件没有过期
            ) {

                //1,添加邮箱状态码
                currentUserinfo.setBitState(BitStatesUtils.addState(currentUserinfo.getBitState(), BitStatesUtils.OP_BIND_EMAIL));

                //2,设置邮箱地址
                currentUserinfo.setEmail(mv.getEmail());

                this.updateByPrimaryKey(currentUserinfo);  //使用本类中的update方法-->乐观锁,
                return true;
            }
        } else {
            //否则绑定失败
            throw new RuntimeException("绑定失败");
        }
        return false;

    }


    //写到这里了2:49 jason
    @Override
    public void bindPhone(String phoneNumber, String verifyCode) {
        //得到verifyCodeVO
        VerifyCodeVO vo = UserContext.getVerifyCodeVO();
        UserInfo currentUserinfo = this.selectByPrimaryKey(UserContext.getCurrentUser().getId());

        System.out.println("是否已经绑定手机号:" + currentUserinfo.getHasBindPhone());


        //能够绑定的条件:vo != null, 填入的手机号码==session里的手机号码, 验证码相同,  时间间隔小于验证码有效时间 ,没有绑定过手机
        if (vo != null //能够绑定的条件:vo != null,
                && phoneNumber.equals(vo.getPhoneNumber())  //填入的手机号码==session里的手机号码
                && verifyCode.equals(vo.getVerifyCode()) //验证码相同,
                && DateUtil.getIntervalSeconds(new Date(), vo.getSendTime()) < Consts.VERIFYCODE_VALID_TIMELIMIT //时间间隔小于验证码有效时间
                && currentUserinfo.getHasBindPhone() == false  //还没有绑定手机
        ) {

            //1,添加手机状态码
            currentUserinfo.setBitState(BitStatesUtils.addState(currentUserinfo.getBitState(), BitStatesUtils.OP_BIND_PHONE));

            //2,设置手机号码
            currentUserinfo.setPhoneNumber(phoneNumber);

            this.updateByPrimaryKey(currentUserinfo);  //使用本类中的update方法-->乐观锁,

            return;
        } else {
            //否则绑定失败
            throw new RuntimeException("绑定失败");
        }
    }


    //修改个人资料信息对应的方法
    @Override
    public void updateBasicInfo(UserInfo vo) {

        //得到当前用户的基本信息资料
        UserInfo userInfo = selectByPrimaryKey(UserContext.getCurrentUser().getId());

        //将vo 里面的信息设置给这个当前用户

        userInfo.setEducationBackground(vo.getEducationBackground());
        userInfo.setHouseCondition(vo.getHouseCondition());
        userInfo.setIncomeGrade(vo.getIncomeGrade());
        userInfo.setKidCount(vo.getKidCount());
        userInfo.setMarriage(vo.getMarriage());

        //如果是第一次写基本资料,添加状态--->已经填写了基本资料

        if (!userInfo.getIsBasicInfo()) {
            userInfo.setBitState(BitStatesUtils.addState(userInfo.getBitState(), BitStatesUtils.OP_BASIC_INFO));
        }
        updateByPrimaryKey(userInfo);
    }


    @Override
    public int save(UserInfo record) {
        int ret = userinfoMapper.insert(record);
        return ret;
    }

    @Override
    public UserInfo selectByPrimaryKey(Long id) {
        return userinfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKey(UserInfo record) {

        int ret = userinfoMapper.updateByPrimaryKey(record);

        if (ret <= 0) {
            throw new RuntimeException("乐观锁失败,userinfo" + record.getId());
        }
    }


}
