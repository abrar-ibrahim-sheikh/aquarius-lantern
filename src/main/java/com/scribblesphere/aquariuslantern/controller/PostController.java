package com.scribblesphere.aquariuslantern.controller;

import com.scribblesphere.aquariuslantern.dto.PostData;
import com.scribblesphere.aquariuslantern.entity.Post;
import com.scribblesphere.aquariuslantern.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostData> savePost(@RequestBody PostData post) {
        PostData newPost = postService.savePost(post);
        return new ResponseEntity<>(newPost, HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostData> updatePost(@PathVariable Long postId, @RequestBody PostData post) {
        PostData updatedPost = postService.updatePost(postId, post);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<HttpStatus> deletePostById(@PathVariable Long postId) {
        postService.deletePostById(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostData> getPostById(@PathVariable Long postId) {
        PostData post = postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<List<PostData>> getPosts(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                   @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        List<PostData> posts = postService.getPosts(page, size);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostData>> getPostsByUser(@PathVariable Long userId) {
        List<PostData> posts = postService.getPostsByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostData>> getPostsByCategory(@PathVariable Long categoryId) {
        List<PostData> posts = postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}
