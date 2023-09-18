package com.scribblesphere.aquariuslantern.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String updatePostImage(MultipartFile file, Long postId);

}
