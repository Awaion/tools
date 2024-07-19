package com.awaion.demo010.base.domain;

import com.awaion.demo010.base.util.BitStatesUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo extends BaseDomain {
    private int version;
    private long bitState;  //状态码
    private String realName;  //真实姓名
    private String idNumber; //身份证号码
    private String phoneNumber;  //对应的手机号码
    private String email; //用户的邮箱

    private int score;  //材料认证分数

    private Long realAuthId; //验证对象对应的id


    private SystemDictionaryItem incomeGrade;
    private SystemDictionaryItem marriage;
    private SystemDictionaryItem kidCount;
    private SystemDictionaryItem educationBackground;
    private SystemDictionaryItem houseCondition;

    //添加已经绑定手机这个属性
    public boolean getHasBindPhone() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_BIND_PHONE);
    }

    //添加已经绑定邮箱这个属性
    public boolean getHasBindEmail() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_BIND_EMAIL);
    }

    //添加已经填写基础资料的状态属性
    public boolean getIsBasicInfo() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_BASIC_INFO);
    }

    //已经实名认证状态
    public boolean getIsRealAuth() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_REAL_AUTH);
    }

    //已经视频认证状态
    public boolean getIsVedioAuth() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_VEDIO_AUTH);
    }


    //是否已经有一个借款在审核流程中
    public boolean getHasBidRequestInProcess() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS);
    }


}
