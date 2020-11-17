package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import com.example.demo.model.Teacher;
import com.example.demo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Subject findById(Long id) {
        return subjectRepository.findById(id).get();
    }

    public void create(Subject subject) {
        subjectRepository.save(subject);
    }

    public void update(Subject subject) {
        subjectRepository.save(subject);
    }

    public void delete(long id) {
        subjectRepository.deleteById(id);
    }

    public List<Subject> findAllByCodeContaining(String code) {
        return subjectRepository.findAllByCodeContaining(code);
    }

    public List<Subject> findAllByNameContaining(String code) {
        return subjectRepository.findAllByNameContaining(code);
    }

    public List<Subject> findAllByTeacher(Teacher teacher) {
        return subjectRepository.findAllByTeacher(teacher);
    }
}
