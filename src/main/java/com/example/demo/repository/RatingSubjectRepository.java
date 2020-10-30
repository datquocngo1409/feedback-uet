package com.example.demo.repository;

import com.example.demo.model.RatingSubject;
import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingSubjectRepository extends JpaRepository<RatingSubject, Long> {
    RatingSubject findByStudentAndSubject(Student student, Subject subject);
}
