package com.scribblesphere.aquariuslantern.service;

import com.scribblesphere.aquariuslantern.dto.CommentData;

public interface CommentService {

    CommentData saveComment(CommentData comment);

    void deleteComment(Long commentId);

    CommentData getComment(Long commentId);

}
