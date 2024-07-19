package com.awaion.demo010.base.service;

import javax.mail.MessagingException;

public interface IVerifyMailService {

    //发送验证邮件的功能
    void sendVerifyEmail(String email) throws MessagingException;


}
