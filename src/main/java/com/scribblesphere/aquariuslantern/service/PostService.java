package com.scribblesphere.aquariuslantern.service;

import com.scribblesphere.aquariuslantern.dto.PostData;
import com.scribblesphere.aquariuslantern.dto.PostResponse;
import com.scribblesphere.aquariuslantern.entity.Post;

import java.util.List;


public interface PostService {
    PostData savePost(PostData post);

    PostData updatePost(Long postId, PostData post);

    void deletePostById(Long postId);

    PostData getPostById(Long postId);

    PostResponse getPosts(Integer page, Integer size, String sort, String dir);

    PostResponse getPostsByUser(Long userId, Integer page, Integer size, String sort, String dir);

    PostResponse getPostsByCategory(Long categoryId, Integer page, Integer size, String sort, String dir);

    PostResponse searchPosts(String keyword, Integer page, Integer size, String sort, String dir);

    Post dataToPost(PostData data);

    PostData postToData(Post post);

}
