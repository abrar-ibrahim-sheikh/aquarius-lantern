package com.scribblesphere.aquariuslantern.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class CommentData implements Serializable {

    private Long commentId;

    private String content;

    private Long postId;

}
