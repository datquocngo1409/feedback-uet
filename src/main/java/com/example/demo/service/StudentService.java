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

    public Student findByMssv(int mssv) {
        return studentRepository.findByMssv(mssv);
    }

    public void create(User user, int mssv) {
        Student student = new Student(user);
        if (mssv <= 0) {
            student.setMssv(getMaxMssv() + 1);
        } else {
            student.setMssv(mssv);
        }
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

    public int getMaxMssv() {
        List<Student> students = studentRepository.findAll();
        int maxMssv = 0;
        for (Student student : students) {
            if (maxMssv < student.getMssv()) {
                maxMssv = student.getMssv();
            }
        }
        return maxMssv;
    }
}
