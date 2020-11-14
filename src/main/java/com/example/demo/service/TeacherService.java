package com.example.demo.service;

import com.example.demo.model.Teacher;
import com.example.demo.model.User;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Teacher findById(Long id) {
        return teacherRepository.findById(id).get();
    }

    public void create(User user) {
        Teacher teacher = new Teacher(user);
        teacherRepository.save(teacher);
    }

    public void update(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void delete(Long id) {
        Teacher teacher = teacherRepository.findById(id).get();
        User user = teacher.getUser();
        teacherRepository.deleteById(id);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    public Teacher findByUser(User user) {
        return teacherRepository.findByUser(user);
    }

    public List<Teacher> findAllByNameContaining(String name) {
        List<User> users = userRepository.findAllByNameContainingAndRole(name, "TEACHER");
        List<Teacher> teachers = new ArrayList<>();
        for (User user : users) {
            Teacher teacher = new Teacher(user);
            teachers.add(teacher);
        }
        return teachers;
    }
}
