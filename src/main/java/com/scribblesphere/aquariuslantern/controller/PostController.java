package com.scribblesphere.aquariuslantern.controller;

import com.scribblesphere.aquariuslantern.config.BlogConstants;
import com.scribblesphere.aquariuslantern.dto.PostData;
import com.scribblesphere.aquariuslantern.dto.PostResponse;
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

    @GetMapping(params = {"page", "size", "sort", "dir"})
    public ResponseEntity<PostResponse> getPosts(@RequestParam(value = "page", defaultValue = BlogConstants.PAGE_NUMBER, required = false) Integer page,
                                                 @RequestParam(value = "size", defaultValue = BlogConstants.PAGE_SIZE, required = false) Integer size,
                                                 @RequestParam(value = "sort", defaultValue = BlogConstants.SORT_BY, required = false) String sort,
                                                 @RequestParam(value = "dir", defaultValue = BlogConstants.SORT_BY, required = false) String dir) {
        PostResponse posts = postService.getPosts(page, size, sort, dir);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}", params = {"page", "size", "sort", "dir"})
    public ResponseEntity<PostResponse> getPostsByUser(@PathVariable Long userId,
                                                         @RequestParam(value = "page", defaultValue = BlogConstants.PAGE_NUMBER, required = false) Integer page,
                                                         @RequestParam(value = "size", defaultValue = BlogConstants.PAGE_SIZE, required = false) Integer size,
                                                         @RequestParam(value = "sort", defaultValue = BlogConstants.SORT_BY, required = false) String sort,
                                                         @RequestParam(value = "dir", defaultValue = BlogConstants.DIRECTION, required = false) String dir) {
        PostResponse posts = postService.getPostsByUser(userId, page, size, sort, dir);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping(value = "/category/{categoryId}", params = {"page", "size", "sort", "dir"})
    public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Long categoryId,
                                                             @RequestParam(value = "page", defaultValue = BlogConstants.PAGE_NUMBER, required = false) Integer page,
                                                             @RequestParam(value = "size", defaultValue = BlogConstants.PAGE_SIZE, required = false) Integer size,
                                                             @RequestParam(value = "sort", defaultValue = BlogConstants.SORT_BY, required = false) String sort,
                                                             @RequestParam(value = "dir", defaultValue = BlogConstants.DIRECTION, required = false) String dir) {
        PostResponse posts = postService.getPostsByCategory(categoryId, page, size, sort, dir);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping(value = "/search", params = {"keyword", "page", "size", "sort", "dir"})
    public ResponseEntity<PostResponse> searchPosts(@RequestParam(value = "keyword") String keyword,
                                                      @RequestParam(value = "page", defaultValue = BlogConstants.PAGE_NUMBER, required = false) Integer page,
                                                      @RequestParam(value = "size", defaultValue = BlogConstants.PAGE_SIZE, required = false) Integer size,
                                                      @RequestParam(value = "sort", defaultValue = BlogConstants.SORT_BY, required = false) String sort,
                                                      @RequestParam(value = "dir", defaultValue = BlogConstants.DIRECTION, required = false) String dir) {
        PostResponse posts = postService.searchPosts(keyword, page, size, sort, dir);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}
