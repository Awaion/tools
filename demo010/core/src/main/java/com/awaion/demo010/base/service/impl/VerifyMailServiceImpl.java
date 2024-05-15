package com.awaion.demo010.base.service.impl;

import com.awaion.demo010.base.domain.Consts;
import com.awaion.demo010.base.domain.UserInfo;
import com.awaion.demo010.base.domain.VerifyEmailVO;
import com.awaion.demo010.base.mapper.VerifyEmailVOMapper;
import com.awaion.demo010.base.service.IUserinfoService;
import com.awaion.demo010.base.service.IVerifyMailService;
import com.awaion.demo010.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

@Service
public class VerifyMailServiceImpl implements IVerifyMailService {

    @Autowired
    private IUserinfoService iUserinfoService;

    @Autowired
    private VerifyEmailVOMapper verifyEmailVOMapper;

    @Value("${application.host}")
    private String hostURL;

    @Value("${email.hostAddr}")
    private String emailHostAddr;

    @Override
    public void sendVerifyEmail(String email) throws MessagingException {

        UserInfo currentUserinfo = iUserinfoService.selectByPrimaryKey(UserContext.getCurrentUser().getId()); //当前登录用户的[用户基本信息]

        if (!currentUserinfo.getHasBindEmail()) {  //没有绑定邮箱才发送邮件
            //创建一个uuid
            String uuid = UUID.randomUUID().toString();
            //构造邮件内容
            //点击<a href="http://localhost/bindEmail.do?key=34957lsjlll">这里</a>完成邮箱绑定,有效期为XX天

            StringBuilder sb = new StringBuilder(100).append("点击<a href=\"").append(hostURL)
                    .append("bindEmail.do?key=").append(uuid).append("\">这里</a>完成邮箱绑定,有效期为").append(Consts.VERIFYEMAIL_VAILID_DAY).append("天");


            //发送邮件
            System.out.println("发送内容:" + sb + "到" + email);
            //==================================================
            JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
            // 设定mail server
            senderImpl.setHost(emailHostAddr);

            // 建立邮件消息,发送简单邮件和html邮件的区别
            MimeMessage mailMessage = senderImpl.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, "utf-8");

            // 设置收件人，寄件人
            messageHelper.setTo(email);
            messageHelper.setFrom("Admin@goobai.com");
            messageHelper.setSubject("绑定验证邮件！");
            // true 表示启动HTML格式的邮件
            messageHelper.setText(sb.toString(), true); //设定要发送的内容

            senderImpl.setUsername("Admin@goobai.com"); // 根据自己的情况,设置username
            senderImpl.setPassword("1111"); // 根据自己的情况, 设置password
            Properties prop = new Properties();
            prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
            prop.put("mail.smtp.timeout", "25000");
            senderImpl.setJavaMailProperties(prop);
            // 发送邮件
            senderImpl.send(mailMessage);

            System.out.println("邮件发送成功..");

            //==================================================


            //如果发送成功,创建一个verifyEmailvo对象并保存
            VerifyEmailVO vo = new VerifyEmailVO();
            vo.setEmail(email);
            vo.setSendTime(new Date());
            vo.setUuid(uuid);
            vo.setUserId(currentUserinfo.getId());
            verifyEmailVOMapper.insert(vo);
        } else {
            throw new RuntimeException("发送邮件失败!");
        }
    }
}
