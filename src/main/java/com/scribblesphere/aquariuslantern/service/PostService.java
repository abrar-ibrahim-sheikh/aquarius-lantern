package com.scribblesphere.aquariuslantern.service;

import com.scribblesphere.aquariuslantern.dto.PostData;
import com.scribblesphere.aquariuslantern.entity.Post;

import java.util.List;


public interface PostService {
    PostData savePost(PostData post);

    PostData updatePost(Long postId, PostData post);

    void deletePostById(Long postId);

    PostData getPostById(Long postId);

    List<PostData> getPosts(Integer page, Integer size);

    List<PostData> getPostsByUser(Long userId);

    List<PostData> getPostsByCategory(Long categoryId);

}
