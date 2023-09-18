package com.scribblesphere.aquariuslantern.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryData implements Serializable {

    private Long categoryId;

    @NotEmpty(message = "Title cannot be empty")
    private String title;

    private String description;

}
