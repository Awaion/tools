package com.awaion.demo010.base.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 上传工具
 *
 * @author Administrator
 */
public class UploadUtil {

    /**
     * 处理文件上传
     *
     * @param file
     * @param basePath 存放文件的目录的绝对路径 servletContext.getRealPath("/upload")
     * @return
     */
    public static String upload(MultipartFile file, String basePath) {
        String orgFileName = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + "."
                + FilenameUtils.getExtension(orgFileName);
        try {
            File targetFile = new File(basePath, fileName);
            FileUtils.writeByteArrayToFile(targetFile, file.getBytes());

            //把文件同步到公共文件夹
//			File publicFile=new File(BidConst.PUBLIC_IMG_SHARE_PATH,fileName);
//			FileUtils.writeByteArrayToFile(publicFile, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
























