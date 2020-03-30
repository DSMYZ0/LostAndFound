package com.qin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UploadController {

    @Value("${laf.host}")
    String imageHost;

    @GetMapping("/uploadImage")
    public String upload(){
        //upload.html
        return "upload";
    }

    @PostMapping("/uploadImage")
    @ResponseBody
    public String upload(@RequestParam("profile") MultipartFile file){

        if(file!=null&&file.getOriginalFilename()!=null){
            String originalFilename=file.getOriginalFilename();
            //获取文件扩展名
            String extendName=originalFilename.substring(originalFilename.lastIndexOf("."));
            //生成唯一文件名
            String uniqueName= UUID.randomUUID().toString();
            //存储(T:/upload/)
            File newFile=new File("I:"+File.separator+"upload",uniqueName+extendName);
            try {
                file.transferTo(newFile);
                //返回图片url
                return imageHost+uniqueName+extendName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
