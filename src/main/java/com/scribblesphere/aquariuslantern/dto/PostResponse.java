package com.scribblesphere.aquariuslantern.dto;

import com.scribblesphere.aquariuslantern.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {

    private List<PostData> content;
    private int pageNumber;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private Boolean isLastPage;

}
