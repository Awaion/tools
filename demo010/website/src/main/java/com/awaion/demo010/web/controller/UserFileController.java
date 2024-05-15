package com.awaion.demo010.web.controller;

import com.awaion.demo010.base.domain.SystemDictionaryItem;
import com.awaion.demo010.base.domain.UserFile;
import com.awaion.demo010.base.service.ISystemDictionaryService;
import com.awaion.demo010.base.service.IUserFileService;
import com.awaion.demo010.base.util.UploadUtil;
import com.awaion.demo010.base.util.UserContext;
import com.awaion.demo010.page.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserFileController {


    @Autowired
    private ServletContext servletContext;
    @Autowired
    private IUserFileService userFileService;

    @Autowired
    private ISystemDictionaryService systemDictionaryService;


    @RequestMapping("/userFile")
    public String userFile(Model model, HttpSession session) {

        /*model.addAttribute("sessionId", session.getId());
        model.addAttribute("userFiles", new ArrayList<>());
        System.out.println("进入Controlle方法里面了");

        return "userFiles";*/

        //判断用户是否选择了类型
        List<UserFile> ufs = userFileService.listUnselectTypeFiles(UserContext.getCurrentUser().getId());


        if (ufs.size() > 0) {  //有资料没选择类型

            List<SystemDictionaryItem> fileTypes = systemDictionaryService.listItemsBySn("userFileType");

            model.addAttribute("userFiles", ufs);
            model.addAttribute("fileTypes", fileTypes);

            return "userFiles_commit";
        } else {
            model.addAttribute("sessionId", session.getId());
            model.addAttribute("userFiles", userFileService.listSelectTypeFiles(UserContext.getCurrentUser().getId()));
            System.out.println("进入Controlle方法里面了");
            return "userFiles";
        }
    }

    //用来上传图片(分控审核资料)

    @RequestMapping("/uploadUserFileImage")
    public void uploadUserFileImage(MultipartFile image) {

        String fileName = UploadUtil.upload(image, this.servletContext.getRealPath("/upload"));
        fileName = "/upload/" + fileName;

        userFileService.createUserFile(fileName);

    }

    @RequestMapping("/userFile_selectType")
    @ResponseBody
    public AjaxResult fileSelectType(Long[] id, Long[] fileType) {

        AjaxResult result = new AjaxResult();
        System.out.println("id数组:" + id);
        System.out.println("fileType数组:" + fileType);

        if (id != null && fileType != null && id.length == fileType.length) {
            userFileService.userFileSelectType(id, fileType);
        }
        return result;
    }
}
