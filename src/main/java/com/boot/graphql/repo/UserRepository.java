package com.boot.graphql.repo;

import com.boot.graphql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByOrderByUserIdDesc();

    boolean existsByUserName(String userName);

    List<User> findByUserNameContaining(String userName);
}
