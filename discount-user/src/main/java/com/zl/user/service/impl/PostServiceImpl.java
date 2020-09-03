package com.zl.user.service.impl;

import com.zl.user.entity.Post;
import com.zl.user.mapper.CommentMapper;
import com.zl.user.mapper.PostMapper;
import com.zl.user.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangliang on 2019/5/24
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostMapper postMapper;

    @Autowired
    CommentMapper commentMapper;




    @Override
    public void insert(Post post, MultipartFile file) {
//        String result = "";
//        try {
//            result = BaiduImageCensorUtil.examineAndUpload(file.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if(result.equals("色情")){
//            throw new ImgException(ErrorCodeConstants.CHECK_IMG_UPLOAD_ERROR);
//        }
//
//        String url = qcloudOSSUtil.upload(file);
//        String uid = requestUtil.getWxToken();
//        post.setId(snowflakeIdWorker.getNextId(IdPrefixSuffixEnum.POST));
//        post.setCreateTime(new Date());
//        post.setCreateUser(uid);
//        post.setUserId(uid);
//        post.setUrl(url);
//        super.insertSelective(post);
    }

    @Override
    public List<Map> getNewPosts(Post post) {
        return postMapper.getNewPosts(post);
    }

    @Override
    public List<Map> getPopularPosts(Post post) {
        return postMapper.getPopularPosts(post);
    }
}
