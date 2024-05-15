package com.awaion.demo010.web.controller;

import com.awaion.demo010.base.domain.RealAuth;
import com.awaion.demo010.base.domain.UserInfo;
import com.awaion.demo010.base.service.IRealAuthService;
import com.awaion.demo010.base.service.IUserinfoService;
import com.awaion.demo010.base.util.UploadUtil;
import com.awaion.demo010.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@Controller
public class RealAuthController {

    @Autowired
    IUserinfoService userinfoService;

    @Autowired
    IRealAuthService realAuthService;

    @Autowired
    private ServletContext servletContext;


    @RequestMapping("/realAuth.do")
    public String toRealAuth(Model model) {
        //得到当前用户
        UserInfo current = userinfoService.selectByPrimaryKey(UserContext.getCurrentUser().getId());

        if (!current.getIsRealAuth()) {  ////1,如果没有通过实名认证,并且realAuthId 为空
            if (current.getRealAuthId() == null) {
                return "realAuth";//去申请页面
            } else { //2,如果没有通过实名认证,并且realAuthId 不为空
                //model添加一个auditing属性
                model.addAttribute("auditing", true);
                return "realAuth_result";
            }

        } else {
            //查询审核认证对象,并传递给下一个页面
            model.addAttribute("auditing", false);
            model.addAttribute("realAuth", realAuthService.selectByPrimaryKey(current.getRealAuthId()));
            return "realAuth_result";
        }
    }

    @RequestMapping("/realAuth_save")
    public String realAuth_save(RealAuth realAuth) {

        realAuthService.apply(realAuth);  //将前台提交的信息封装成realAuth对象,并交个service去处理--->申请

        return "redirect:/realAuth.do";
    }

    //上传图片对应的方法

    @RequestMapping("/uploadImg")
    @ResponseBody
    public String uploadImg(MultipartFile image) {

        String fileName = UploadUtil.upload(image, this.servletContext.getRealPath("/upload"));
        return "/upload/" + fileName;
    }


}
