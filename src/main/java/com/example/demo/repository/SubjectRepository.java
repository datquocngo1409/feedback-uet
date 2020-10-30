package com.example.demo.repository;

import com.example.demo.model.Subject;
import com.example.demo.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findAllByTeacher(Teacher teacher);
    List<Subject> findAllByCodeContaining(String code);
    List<Subject> findAllByNameContaining(String name);
}
