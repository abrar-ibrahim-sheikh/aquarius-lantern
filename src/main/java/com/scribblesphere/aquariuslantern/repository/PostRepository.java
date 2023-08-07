package com.scribblesphere.aquariuslantern.repository;

import com.scribblesphere.aquariuslantern.entity.Category;
import com.scribblesphere.aquariuslantern.entity.Post;
import com.scribblesphere.aquariuslantern.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUser(User userId);
    List<Post> findByCategory(Category categoryId);



}
