package com.example.demo.repository.user;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository  extends JpaRepository<User,Long> {
    User findByUsername(String username);
    boolean findUsersByUsername(String username);
}