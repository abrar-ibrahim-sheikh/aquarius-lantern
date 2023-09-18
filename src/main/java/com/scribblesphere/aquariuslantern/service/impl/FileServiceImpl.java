package com.scribblesphere.aquariuslantern.service.impl;

import com.scribblesphere.aquariuslantern.dto.PostData;
import com.scribblesphere.aquariuslantern.entity.Post;
import com.scribblesphere.aquariuslantern.exception.ResourceNotFoundException;
import com.scribblesphere.aquariuslantern.repository.PostRepository;
import com.scribblesphere.aquariuslantern.service.FileService;
import com.scribblesphere.aquariuslantern.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.*;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Value("${project.uploads.images}")
    private String imagePath;

    @Autowired
    private PostService postService;

    public String uploadImage(MultipartFile file, Long postId) {
        String originalFileName = file.getOriginalFilename();
        try {
            InputStream imageInputStream = file.getInputStream();
            String destinationPath = imagePath + "/" + postId + "_" + originalFileName;
            Path finalPath = Paths.get(destinationPath);
            log.info("Before Uploading image with originalFileName:{} | destinationPath:{} | path:{}",
                    originalFileName, destinationPath, finalPath);
            Files.copy(imageInputStream, finalPath, StandardCopyOption.REPLACE_EXISTING);
            log.info("After Uploading image with originalFileName:{} | destinationPath:{} | path:{}",
                    originalFileName, destinationPath, finalPath.toString());
            return destinationPath;
        } catch (Exception e) {
            log.error("Error: Occurred while trying to upload image", e);
        }
        return null;
    }

    @Override
    public String updatePostImage(MultipartFile file, Long postId) {
        String uploadedPath = uploadImage(file, postId);
        PostData post = postService.getPostById(postId);
        post.setImageUrl(uploadedPath);
        postService.updatePost(postId, post);
        return uploadedPath;
    }
}
