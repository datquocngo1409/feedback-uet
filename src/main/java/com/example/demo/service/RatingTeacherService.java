package com.example.demo.service;

import com.example.demo.model.RatingSubject;
import com.example.demo.model.RatingTeacher;
import com.example.demo.model.Subject;
import com.example.demo.model.Teacher;
import com.example.demo.repository.RatingTeacherRepository;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingTeacherService {
    @Autowired
    private RatingTeacherRepository ratingTeacherRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<RatingTeacher> findAll() {
        return ratingTeacherRepository.findAll();
    }

    public RatingTeacher findById(Long id) {
        return ratingTeacherRepository.findById(id).get();
    }

    public void rate(RatingTeacher ratingTeacher) {
        RatingTeacher oldRating = ratingTeacherRepository.findByStudentAndTeacher(ratingTeacher.getStudent(), ratingTeacher.getTeacher());
        Teacher teacher = teacherRepository.findById(ratingTeacher.getTeacher().getId()).get();
        if (oldRating == null) {
            teacher.setRating((teacher.getRating() * teacher.getRatingCount() + ratingTeacher.getPoint())/(teacher.getRatingCount() + 1));
            teacherRepository.save(teacher);
            ratingTeacherRepository.save(ratingTeacher);
        } else {
            teacher.setRating((teacher.getRating() * teacher.getRatingCount() + ratingTeacher.getPoint() - oldRating.getPoint())/(teacher.getRatingCount()));
            teacherRepository.save(teacher);
            ratingTeacher.setId(oldRating.getId());
            ratingTeacherRepository.save(ratingTeacher);
        }
    }

    public void delete(Long id) {
        ratingTeacherRepository.deleteById(id);
    }
}
