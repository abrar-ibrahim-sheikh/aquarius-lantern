package com.scribblesphere.aquariuslantern.vo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

@Data
public class PostData implements Serializable {

    private Long postId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private String imageUrl;

    private Long categoryId;

    private Long userId;

    private List<CommentData> comments = new LinkedList<>();

    private Instant createdOn;

    private Instant updatedOn;



}
