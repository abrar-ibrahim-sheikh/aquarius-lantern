package com.scribblesphere.aquariuslantern.repository;

import com.scribblesphere.aquariuslantern.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

}
