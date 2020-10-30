package com.example.demo.repository;

import com.example.demo.model.Teacher;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByToken(String token);
    public List<User> findAllByNameContainingAndRole(String name, String role);
}
