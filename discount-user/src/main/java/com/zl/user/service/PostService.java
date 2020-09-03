package com.zl.user.service;

import com.zl.user.entity.Feedback;
import com.zl.user.entity.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangliang on 2019/5/24
 */
public interface PostService {
     void insert(Post post, MultipartFile file);

    List<Map> getNewPosts(Post post);

    List<Map> getPopularPosts(Post post);
}
