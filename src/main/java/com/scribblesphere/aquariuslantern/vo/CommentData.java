package com.scribblesphere.aquariuslantern.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class CommentData implements Serializable {

    private Long commentId;

    private String content;

    private Long postId;

}
