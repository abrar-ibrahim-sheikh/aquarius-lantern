package com.scribblesphere.aquariuslantern.dto;

import com.scribblesphere.aquariuslantern.entity.Category;
import com.scribblesphere.aquariuslantern.entity.Comment;
import com.scribblesphere.aquariuslantern.entity.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
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

//    private Category category;

//    private User user;

    private Long categoryId;

    private Long userId;

    private List<CommentData> comments = new LinkedList<>();

    private Instant createdOn;

    private Instant updatedOn;



}
