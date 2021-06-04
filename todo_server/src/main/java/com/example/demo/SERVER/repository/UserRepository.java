package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserById(Long id);
}
