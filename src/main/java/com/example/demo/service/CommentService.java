package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.model.Subject;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public List<Comment> findAllBySubject(Subject subject) {
        return commentRepository.findAllBySubject(subject);
    }

    public List<Comment> findAllBySubjectAndReply(Subject subject, boolean reply) {
        return commentRepository.findAllBySubjectAndReply(subject, reply);
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id).get();
    }

    public void create(Comment comment) {
        commentRepository.save(comment);
    }

    public void update(Comment comment) {
        commentRepository.save(comment);
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
