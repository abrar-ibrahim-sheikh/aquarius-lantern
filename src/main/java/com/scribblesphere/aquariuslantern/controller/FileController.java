package com.scribblesphere.aquariuslantern.controller;

import com.scribblesphere.aquariuslantern.entity.Post;
import com.scribblesphere.aquariuslantern.repository.PostRepository;
import com.scribblesphere.aquariuslantern.service.FileService;
import com.scribblesphere.aquariuslantern.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping(value = "/upload/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam(value = "file")MultipartFile file,
                                              @RequestParam(value = "postId") Long postId) {
        String path = fileService.updatePostImage(file, postId);
        return new ResponseEntity<>("success: Stored path: " + path, HttpStatus.OK);
    }
}
