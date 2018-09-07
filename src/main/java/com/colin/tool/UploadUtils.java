package com.colin.tool;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class UploadUtils {

    public static String uploadFile(MultipartFile file,String path) throws IOException {
        String name = file.getOriginalFilename(); //上传文件的名字
        String suffixName = name.substring(name.indexOf("."));
        String hash = Integer.toHexString(new Random().nextInt());
        String fileName = hash + suffixName;
        File tempFile = new File(path,fileName);
        if(!tempFile.getParentFile().exists()){
            //tempFile.getParentFile().mkdir();
            tempFile.mkdirs();
        }else{
            tempFile.delete();
        }
        tempFile.createNewFile();
        file.transferTo(tempFile);
        return tempFile.getName();

    }
}
