package com.example.demo.repository;

import com.example.demo.model.Teacher;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    public Teacher findByUser(User user);
}
