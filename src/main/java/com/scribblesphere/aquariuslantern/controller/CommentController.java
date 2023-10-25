package com.scribblesphere.aquariuslantern.controller;

import com.scribblesphere.aquariuslantern.vo.CommentData;
import com.scribblesphere.aquariuslantern.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping
    private ResponseEntity<CommentData> getComment(@RequestParam("commentId") Long commentId) {
        CommentData data = commentService.getComment(commentId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<CommentData> saveComment(@RequestBody CommentData commentData) {
        CommentData data = commentService.saveComment(commentData);
        return new ResponseEntity<CommentData>(data, HttpStatus.OK);
    }

    @DeleteMapping
    private ResponseEntity<HashMap<String, Long>> saveComment(@RequestParam("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        HashMap<String, Long> response = new HashMap<>();
        response.put("commentId", commentId);
        return new ResponseEntity<HashMap<String, Long>>(response, HttpStatus.OK);
    }

}
