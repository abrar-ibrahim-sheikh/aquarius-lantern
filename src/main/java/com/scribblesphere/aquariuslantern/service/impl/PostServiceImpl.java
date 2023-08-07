package com.scribblesphere.aquariuslantern.service.impl;

import com.scribblesphere.aquariuslantern.dto.PostData;
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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<PostData> getPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::postToData)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostData> getPostsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", String.valueOf(userId)));
        List<Post> posts = postRepository.findByUser(user);
        return posts.stream()
                .map(this::postToData)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostData> getPostsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", String.valueOf(categoryId)));
        List<Post> posts = postRepository.findByCategory(category);
        return posts.stream()
                .map(this::postToData)
                .collect(Collectors.toList());
    }

    private Post dataToPost(PostData data) {
        return modelMapper.map(data, Post.class);
    }

    private PostData postToData(Post post) {
        return modelMapper.map(post, PostData.class);
    }

}
