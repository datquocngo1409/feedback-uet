package com.example.demo.model;

import com.example.demo.model.dto.CommentRequestDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Subject subject;

    private String studentName;

    private String studentAvatar;

    private String content;

    private String creationTime;

    private boolean isReply;

    private String replyCommentId;

    private int likeNumber;

    public Comment() {
    }

    public Comment(Long id, Student student, Subject subject, String content, String creationTime, boolean isReply, String replyCommentId, int likeNumber) {
        this.id = id;
        this.student = student;
        this.subject = subject;
        this.studentName = student.getUser().getName();
        this.studentAvatar = student.getUser().getAvatar();
        this.content = content;
        this.creationTime = new Date().toString();
        this.isReply = isReply;
        this.replyCommentId = replyCommentId;
        this.likeNumber = likeNumber;
    }

    public Comment(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
        this.isReply = commentRequestDto.isReply();
        this.replyCommentId = commentRequestDto.getReplyCommentId();
        this.likeNumber = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        this.creationTime = dtf.format(now);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentAvatar() {
        return studentAvatar;
    }

    public void setStudentAvatar(String studentAvatar) {
        this.studentAvatar = studentAvatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public boolean isReply() {
        return isReply;
    }

    public void setReply(boolean reply) {
        isReply = reply;
    }

    public String getReplyCommentId() {
        return replyCommentId;
    }

    public void setReplyCommentId(String replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }
}
