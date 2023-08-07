package com.scribblesphere.aquariuslantern.dto;

import com.scribblesphere.aquariuslantern.entity.Category;
import com.scribblesphere.aquariuslantern.entity.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
public class PostData implements Serializable {

    private Long postId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

//    private Category category;

//    private User user;

    private Long categoryId;

    private Long userId;

    private Instant createdOn;

    private Instant updatedOn;



}
