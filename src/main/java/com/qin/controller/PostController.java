package com.qin.controller;

import com.qin.common.ServerResponse;
import com.qin.pojo.Post;
import com.qin.service.IPostService;
import com.qin.util.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/portal/post/")
public class PostController {

    @Resource
    IPostService postService;
    @Value("${upload.path}")
    String uploadPath;
    @Value("${laf.host}")
    String imageHost;

    @RequestMapping("retrieve")
    public ServerResponse retrieve(Integer categoryId,
                               @RequestParam(value = "time",defaultValue = "2020-03-29") String time,
                               String address,
                               Integer LoF,
                               String keyword,
                               Integer userId,
                               @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                               @RequestParam(value = "orderBy",defaultValue = "time_desc") String orderBy){
        return postService.retrieve(categoryId, DateUtils.string2Date(time),address,LoF,keyword,userId,pageNum,pageSize,orderBy);
    }

    @RequestMapping("carousel")
    public ServerResponse getCarousel(){
        return postService.getCarousel();
    }

    @RequestMapping("add")
    public ServerResponse addPost( String name, Integer categoryId, String time, String address, String detail, String pictureUrl, @RequestParam("profile") MultipartFile file, Integer LoF, Integer userId){

        return postService.addPost(toPost( null,name,categoryId, time,address, detail, pictureUrl,  file, LoF,userId));
    }

    @RequestMapping("update")
    public ServerResponse updatePost( Integer id,String name, Integer categoryId, String time, String address, String detail, String pictureUrl, @RequestParam("profile") MultipartFile file, Integer LoF){

        return postService.updatePost(toPost( id,name,categoryId, time,address, detail, pictureUrl,  file, LoF,null));
    }

    @RequestMapping("delete")
    public ServerResponse deletePost(Integer id){
        return postService.deletePost(id);
    }

    public Post toPost( Integer id,String name, Integer categoryId, String time, String address, String detail, String pictureUrl, @RequestParam("profile") MultipartFile profile, Integer LoF,Integer userId) {
        Post post = new Post();

        post.setId(id);
        post.setName(name);
        post.setCategoryId(categoryId);

        if (time == null) {
            time = new java.sql.Date(System.currentTimeMillis()).toString();
        }

        post.setTime(DateUtils.string2Date(time));
        post.setAddress(address);
        post.setDetail(detail);
        post.setPictureUrl(pictureUrl);
        post.setLoF(LoF);
        post.setUserId(userId);

        if (profile != null && profile.getOriginalFilename() != null) {
            String originalFilename = profile.getOriginalFilename();
            //获取文件扩展名
            String extendName = originalFilename.substring(originalFilename.lastIndexOf("."));
            //生成唯一文件名
            String uniqueName = UUID.randomUUID().toString();
            //存储(I:/upload/)
            File newFile = new File(uploadPath, uniqueName + extendName);
            try {
                profile.transferTo(newFile);
                //返回图片url
                post.setPicture(uniqueName + extendName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return post;
    }
}
