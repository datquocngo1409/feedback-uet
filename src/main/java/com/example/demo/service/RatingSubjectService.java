package com.example.demo.service;

import com.example.demo.model.RatingSubject;
import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import com.example.demo.model.User;
import com.example.demo.repository.RatingSubjectRepository;
import com.example.demo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingSubjectService {
    @Autowired
    private RatingSubjectRepository ratingSubjectRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public List<RatingSubject> findAll() {
        return ratingSubjectRepository.findAll();
    }

    public RatingSubject findById(Long id) {
        return ratingSubjectRepository.findById(id).get();
    }

    public void rate(RatingSubject ratingSubject) {
        RatingSubject oldRating = ratingSubjectRepository.findByStudentAndSubject(ratingSubject.getStudent(), ratingSubject.getSubject());
        Subject subject = subjectRepository.findById(ratingSubject.getSubject().getId()).get();
        if (oldRating == null) {
            subject.setRating((subject.getRating() * subject.getRatingCount() + ratingSubject.getPoint())/(subject.getRatingCount() + 1));
            subject.setRatingCount(subject.getRatingCount() + 1);
            subjectRepository.save(subject);
            ratingSubjectRepository.save(ratingSubject);
        } else {
            subject.setRating((subject.getRating() * subject.getRatingCount() + ratingSubject.getPoint() - oldRating.getPoint())/(subject.getRatingCount()));
            subjectRepository.save(subject);
            ratingSubject.setId(oldRating.getId());
            ratingSubjectRepository.save(ratingSubject);
        }
    }

    public void delete(Long id) {
        ratingSubjectRepository.deleteById(id);
    }
}
