package com.scribblesphere.aquariuslantern.service.impl;

import com.scribblesphere.aquariuslantern.vo.PostData;
import com.scribblesphere.aquariuslantern.vo.PostResponse;
import com.scribblesphere.aquariuslantern.entity.Category;
import com.scribblesphere.aquariuslantern.entity.Post;
import com.scribblesphere.aquariuslantern.entity.User;
import com.scribblesphere.aquariuslantern.exception.ResourceNotFoundException;
import com.scribblesphere.aquariuslantern.repository.CategoryRepository;
import com.scribblesphere.aquariuslantern.repository.PostRepository;
import com.scribblesphere.aquariuslantern.repository.UserRepository;
import com.scribblesphere.aquariuslantern.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostData savePost(PostData data) {
        Post post = dataToPost(data);
        return postToData(postRepository.save(post));
    }

    @Override
    public PostData updatePost(Long postId, PostData data) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", String.valueOf(postId)));
        User user = userRepository.findById(data.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", String.valueOf(data.getUserId())));
        Category category = categoryRepository.findById(data.getCategoryId())
                        .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", String.valueOf(data.getCategoryId())));
        if (data.getImageUrl() != null)
            post.setImageUrl(data.getImageUrl());
        post.setTitle(data.getTitle());
        post.setContent(data.getContent());
        post.setCategory(category);
        post.setUser(user);
        return postToData(postRepository.save(post));
    }

    @Override
    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostData getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", String.valueOf(postId)));
        return postToData(post);
    }

    @Override
    public PostResponse getPosts(Integer page, Integer size, String sort, String dir) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> pagePost = postRepository.findAll(pageable);
        List<Post> posts = pagePost.getContent();
        List<PostData> content=  posts.stream()
                .map(this::postToData)
                .toList();

        return getPostResponse(content, pagePost);
    }

    @Override
    public PostResponse getPostsByUser(Long userId, Integer page, Integer size, String sort, String dir) {
        Sort sortBy = dir.equalsIgnoreCase("ASC") ? Sort.by(sort).ascending() : Sort.by(sort).descending();
        Pageable pageable = PageRequest.of(page, size, sortBy);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", String.valueOf(userId)));
        Page<Post> pagePost = postRepository.findByUser(user, pageable);
        List<Post> posts = pagePost.getContent();
        List<PostData> content = posts.stream()
                .map(this::postToData)
                .toList();
        return getPostResponse(content, pagePost);
    }

    @Override
    public PostResponse getPostsByCategory(Long categoryId, Integer page, Integer size, String sort, String dir) {
        Sort sortBy = dir.equalsIgnoreCase("ASC") ? Sort.by(sort).ascending() : Sort.by(sort).descending();
        Pageable pageable = PageRequest.of(page, size, sortBy);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", String.valueOf(categoryId)));
        Page<Post> pagePost = postRepository.findByCategory(category, pageable);
        List<Post> posts = pagePost.getContent();
        List<PostData> content = posts.stream()
                .map(this::postToData)
                .toList();
        return getPostResponse(content, pagePost);
    }

    @Override
    public PostResponse searchPosts(String keyword, Integer page, Integer size, String sort, String dir) {
        Sort sortBy = dir.equalsIgnoreCase("ASC") ? Sort.by(sort).ascending() : Sort.by(sort).descending();
        Pageable pageable = PageRequest.of(page, size, sortBy);
        Page<Post> pagePost = postRepository.findByContentContaining(keyword, pageable);
        List<Post> posts = pagePost.getContent();
        List<PostData> content =  posts.stream()
                .map(this::postToData)
                .toList();
        return getPostResponse(content, pagePost);
    }

    @Override
    public Post dataToPost(PostData data) {
        return modelMapper.map(data, Post.class);
    }

    @Override
    public PostData postToData(Post post) {
        return modelMapper.map(post, PostData.class);
    }

    private PostResponse getPostResponse(List<PostData> content, Page<Post> pagePost) {
        PostResponse response = new PostResponse();
        response.setContent(content);
        response.setPageNumber(pagePost.getNumber());
        response.setPageSize(pagePost.getSize());
        response.setTotalElements(pagePost.getTotalElements());
        response.setTotalPages(pagePost.getTotalPages());
        response.setIsLastPage(pagePost.isLast());
        return response;
    }

}
