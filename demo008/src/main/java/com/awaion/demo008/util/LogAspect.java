package com.awaion.demo008.util;

import com.awaion.demo008.domain.Employee;
import com.awaion.demo008.domain.SystemLog;
import com.awaion.demo008.service.ISystemLogService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class LogAspect {
    @Setter
    private ISystemLogService systemLogService;

    public void writeLog(JoinPoint jp) {
        try {
            //把信息封装到Systemlog对象里面
            SystemLog log = new SystemLog();
            //设置操作时间
            log.setOpTime(new Date());
            //设置操作人
            log.setOpUser((Employee) SecurityUtils.getSubject().getPrincipal());
            //操作了哪个方法
            String functio = jp.getSignature().getDeclaringTypeName() + ":" + jp.getSignature().getName();
            log.setFunctio(functio);
            //操作参数
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            log.setParams(mapper.writeValueAsString(jp.getArgs()));

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //设置IP
            log.setOpIP(request.getRemoteAddr());

            //将日志保存入库
            systemLogService.insert(log);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }


}
