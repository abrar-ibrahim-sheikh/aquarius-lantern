package com.scribblesphere.aquariuslantern.service.impl;

import com.scribblesphere.aquariuslantern.dto.CommentData;
import com.scribblesphere.aquariuslantern.entity.Comment;
import com.scribblesphere.aquariuslantern.entity.Post;
import com.scribblesphere.aquariuslantern.exception.ResourceNotFoundException;
import com.scribblesphere.aquariuslantern.repository.CommentRepository;
import com.scribblesphere.aquariuslantern.repository.PostRepository;
import com.scribblesphere.aquariuslantern.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentData saveComment(CommentData commentData) {
        Post post = postRepository.findById(commentData.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", String.valueOf(commentData.getPostId())));
        Comment comment = dataToComment(commentData);
        return commentToData(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentData getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", String.valueOf(commentId)));
        return commentToData(comment);
    }

    private Comment dataToComment(CommentData data) {
        return this.modelMapper.map(data, Comment.class);
    }

    private CommentData commentToData(Comment comment) {
        return this.modelMapper.map(comment, CommentData.class);
    }
}
