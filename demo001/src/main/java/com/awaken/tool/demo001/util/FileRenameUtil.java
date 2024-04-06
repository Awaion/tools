package com.awaken.tool.demo001.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.file.FileSystemDirectory;
import com.drew.metadata.mp4.Mp4Directory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FileRenameUtil {
    public static void main(String[] args) {
        // 本地文件夹路径
        String path = "D:\\2024\\相机\\照片1";

        File filePath = new File(path);

        // false表示读取文件修改时间重命名,true表示数字递增重命名
        boolean setTime = false;
        if (setTime) {
            for (File file : filePath.listFiles()) {
                Metadata metadata;
                try {
                    metadata = ImageMetadataReader.readMetadata(file);
                } catch (ImageProcessingException e) {
                    e.printStackTrace();
                    continue;
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }
                String time = getDate(metadata);
                String fileName = path + time + "." + FileUtil.getSuffix(file).toLowerCase();
                // 重命名生效代码
//                file.renameTo(new File(fileName));
                // 打印生效后文件名
                System.out.println(file.getName() + "->" + fileName);
            }
        } else {
            Integer start = 240110000;
            for (File file : filePath.listFiles()) {
                String fileName = path + ++start + "." + FileUtil.getSuffix(file);
                // 重命名生效代码
//                file.renameTo(new File(fileName));
                // 打印生效后文件名
                System.out.println(file.getName() + "->" + fileName);
            }
        }
    }


    public static String getDate(Metadata metadata) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String date0 = dtf.format(LocalDateTime.now());
        if (metadata == null) {
            return date0 + RandomUtil.randomNumbers(3);
        }

        // 如果能够获取exfi的日期数据，以该日期为准
        ExifDirectoryBase exifDir = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
        if (exifDir != null) {
            date0 = exifDir.getString(ExifDirectoryBase.TAG_DATETIME_ORIGINAL);
            if (date0 != null) {
                String[] s = date0.split(" ");
                return s[0].replace(":", "") + s[1].replace(":", "") + RandomUtil.randomNumbers(3);
            }
        }

        // mp4
        Mp4Directory mp4Dir = metadata.getFirstDirectoryOfType(Mp4Directory.class);
        if (mp4Dir != null) {
            Date date = mp4Dir.getDate(Mp4Directory.TAG_CREATION_TIME);
            if (date != null) {
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                try {
                    date0 = df.format(date);
                } catch (IllegalArgumentException e) {
                    // 如果是个不清楚的日期类型，返回该类型的toString结果
                    date0 = date.toString();
                }
                return date0 + RandomUtil.randomNumbers(3);
            }
        }

        // 否则以文件修改时间为准
        FileSystemDirectory fileDir = metadata.getFirstDirectoryOfType(FileSystemDirectory.class);
        if (fileDir != null) {
            Object o = fileDir.getObject(FileSystemDirectory.TAG_FILE_MODIFIED_DATE);

            if (o != null) {
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                try {
                    date0 = df.format(o);
                } catch (IllegalArgumentException e) {
                    // 如果是个不清楚的日期类型，返回该类型的toString结果
                    date0 = o.toString();
                    System.out.println(o.getClass());
                    System.out.println(fileDir.getString(FileSystemDirectory.TAG_FILE_NAME) + ": " + date0);
                }
                return date0 + RandomUtil.randomNumbers(3);
            }
        }
        // 如果什么时间信息都没有获取到，返回当前时间
        System.out.println("无法获取资源时间相关信息，使用当前时间...");
        return dtf.format(LocalDateTime.now()) + RandomUtil.randomNumbers(3);
    }

    public static void printAll(File file) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }
        }
    }

}
