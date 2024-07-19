package com.awaion.demo010.base.service.impl;

import com.awaion.demo010.base.domain.*;
import com.awaion.demo010.base.mapper.IplogMapper;
import com.awaion.demo010.base.mapper.LogininfoMapper;
import com.awaion.demo010.base.service.IAccountService;
import com.awaion.demo010.base.service.ILogininfoService;
import com.awaion.demo010.base.service.IUserinfoService;
import com.awaion.demo010.base.util.UserContext;
import com.awaion.demo010.business.domain.ExpAccount;
import com.awaion.demo010.business.mapper.ExpAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogininfoServiceImpl implements ILogininfoService {

    @Autowired
    private LogininfoMapper logininfoMapper;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private IplogMapper iplogMapper;

    @Autowired
    private ExpAccountMapper expAccountMapper;

    @Override
    public Logininfo login(String username, String password, String ip, int userType) {

        //根据用户名和密码查询这个用户
        Logininfo logininfo = logininfoMapper.checkByUsernameAndPwd(username, password, userType);

        //创建登录记录的对象
        IpLog ipLog = new IpLog();

        ipLog.setUsername(username);
        ipLog.setLoginTime(new Date());
        ipLog.setIp(ip);
        ipLog.setUserType(userType);

        IpLog lastLog = new IpLog();

        //获取上次登录的登录iplog对象
        if (iplogMapper.getLastIpLog(username) != null && iplogMapper.getLastIpLog(username).size() > 0) {
            lastLog = iplogMapper.getLastIpLog(username).get(0);
            System.out.println("lastLog-->" + lastLog);
        }

        //如果查询到,则登录成功
        if (logininfo != null) {
            //将登录的用户信息放到session中,返回--->还差这一步
            UserContext.setCurrentUser(logininfo);
            ipLog.setState(IpLog.STATE_SUCCESS); //设置登录状态为登录成功

            UserContext.setLastLog(lastLog);  //将上次登录的iplog日志放到session中

        } else {
            //否则抛出异常
            ipLog.setState(IpLog.STATE_FAILED); //设置登录状态为登录失败
        }
        iplogMapper.insert(ipLog);  //将登录日志对象保存到数据库中
        return logininfo;
    }


    @Override
    public Logininfo register(String username, String password) {

        //判断用户名是否存在
        int count = logininfoMapper.selectCountByUsername(username);

        //如果不存在
        if (count <= 0) {

            // 创建一个logininfo对象
            Logininfo logininfo = new Logininfo();
            //设置相关属性

            logininfo.setUsername(username);
            logininfo.setPassword(password);
            logininfo.setState(Logininfo.STATE_NORMAL);
            logininfo.setUserType(Logininfo.TYPE_CLIENT);  //只有前台用户有注册的功能,管理员通过添加的来,所以这里直接设置为TYPE_CLIENT

            //保存
            logininfoMapper.insert(logininfo);  //保存登录信息对象

            //==========
            //创建账户对象和用户基本信息对象,并且保存
            Account account = new Account();
            account.setId(logininfo.getId());
            accountService.save(account);  //保存账户对象
            //-------------------
            UserInfo userInfo = new UserInfo();
            userInfo.setId(logininfo.getId());
            userinfoService.save(userInfo);  //保存用户基本信息对象
            //==========

            //-------------创建体验金账户对象----------------
            ExpAccount expAccount = new ExpAccount();
            expAccount.setId(logininfo.getId());
            expAccount.setUsableAmount(Consts.EXPACCOUNT_INIT_USABLE_AMOUNT);//
            expAccount.setFreezedAmount(Consts.ZERO);
            expAccountMapper.insert(expAccount);
            //-------------创建体验金账户对象----------------

            return logininfo;
        } else {
            //如果存在
            // 抛出一个异常
            throw new RuntimeException("用户名已经存在了!");
        }
    }

    @Override
    public int insert(Logininfo record) {
        return 0;
    }

    @Override
    public Logininfo selectByPrimaryKey(Long id) {
        return logininfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Logininfo record) {
        return logininfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public boolean checkUsername(String username) {


        //判断用户名是否存在
        int count = logininfoMapper.selectCountByUsername(username);

        if (count <= 0) {//如果不存在
            return true;  //表示用户名没有被注册
        } else {//用户名存在
            return false; //表示用户名已经被注册了
        }
    }


}
