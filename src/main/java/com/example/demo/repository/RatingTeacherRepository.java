package com.example.demo.repository;

import com.example.demo.model.RatingTeacher;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingTeacherRepository extends JpaRepository<RatingTeacher, Long> {
    RatingTeacher findByStudentAndTeacher(Student student, Teacher teacher);
}
