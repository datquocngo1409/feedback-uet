package com.example.demo.service;

import com.example.demo.model.Teacher;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }

    public List<Teacher> findAllByNameContaining(String name) {
        List<User> users = userRepository.findAllByNameContainingAndRole(name, "STUDENT");
        List<Teacher> teachers = new ArrayList<>();
        for (User user : users) {
            Teacher teacher = new Teacher(user);
            teachers.add(teacher);
        }
        return teachers;
    }
}
