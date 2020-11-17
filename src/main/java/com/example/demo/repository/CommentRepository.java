package com.example.demo.repository;

import com.example.demo.model.Comment;
import com.example.demo.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findAllBySubject(Subject subject);
    public List<Comment> findAllBySubjectAndReply(Subject subject, boolean reply);
}
