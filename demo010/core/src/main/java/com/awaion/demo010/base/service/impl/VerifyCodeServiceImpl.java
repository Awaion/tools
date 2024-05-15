package com.awaion.demo010.base.service.impl;

import com.awaion.demo010.base.domain.Consts;
import com.awaion.demo010.base.domain.VerifyCodeVO;
import com.awaion.demo010.base.service.IVerifyCodeService;
import com.awaion.demo010.base.util.DateUtil;
import com.awaion.demo010.base.util.UserContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {

    @Value("${sms.url}")
    private String url;
    @Value("${sms.username}")
    private String username;
    @Value("${sms.password}")
    private String password;
    @Value("${sms.APIkey}")
    private String APIkey;


    @Override
    public void sendVerifyCode(String phoneNumber) {

        //判断是否应该发送验证码
        VerifyCodeVO vo = UserContext.getVerifyCodeVO();

        if (vo == null //之前没有发送过验证码
                || (vo != null && DateUtil.getIntervalSeconds(vo.getSendTime(), new Date()) > Consts.SEND_VERIFYCODE_INTERVAL)) { //离上次发送验证码已经超过规定时间间隔

            //生成验证码
            String verifyCode = UUID.randomUUID().toString().substring(0, 4);

            //调用第三方接口,发送验证码
            /*System.out.println("发送验证码:" + verifyCode + "到手机:" + phoneNumber);*/

            //=================发送短信的功能=================================

            try {
                //创建一个url对象
                URL url = new URL(this.url);
                //-->创建连接对象
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //设置相关属性
                conn.setRequestMethod("POST");

                //设置要输出实体内容
                conn.setDoOutput(true);
                //设置输出内容--->拼接内容
                StringBuilder sb = new StringBuilder(100).append("username=").append(username)
                        .append("&password=").append(password).append("&APIkey=").append(APIkey)
                        .append("&phoneNumber=").append(phoneNumber).append("&content=").append("验证码:").append(verifyCode)
                        .append("请及时使用,有效期:").append(Consts.VERIFYCODE_VALID_TIMELIMIT).append("分钟");

                conn.getOutputStream().write(sb.toString().getBytes("UTF-8"));
                //发送
                conn.connect();
                //获取响应
                String ret = StreamUtils.copyToString(conn.getInputStream(), Charset.forName("UTF-8"));
                System.out.println(ret);

                if (ret.indexOf("success:") == 0) {
                    VerifyCodeVO newvo = new VerifyCodeVO(phoneNumber, new Date(), verifyCode);
                    UserContext.setVerifyCodeVO(newvo);
                } else {
                    throw new RuntimeException();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            //=====================发送短信的功能=============================
            //将VerifyCodeVO对象放到session中
            /*VerifyCodeVO newvo = new VerifyCodeVO(phoneNumber, new Date(), verifyCode);

            UserContext.setVerifyCodeVO(newvo);*/
        }
    }
}
