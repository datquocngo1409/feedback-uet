package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.model.User;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).get();
    }

    public void create(User user) {
        Student student = new Student(user);
        studentRepository.save(student);
    }

    public void update(Student student) {
        studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public Student findByUser(User user) {
        return studentRepository.findByUser(user);
    }

    public List<Student> findAllByNameContaining(String name) {
        List<User> users = userRepository.findAllByNameContainingAndRole(name, "STUDENT");
        List<Student> students = new ArrayList<>();
        for (User user : users) {
            Student student = new Student(user);
            students.add(student);
        }
        return students;
    }
}
