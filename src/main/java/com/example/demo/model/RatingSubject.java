package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class RatingSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Subject subject;

    private int point;

    private String studentName;

    private String studentAvatar;

    private String content;

    private String creationTime;

    public RatingSubject() {
    }

    public RatingSubject(Long id, Student student, Subject subject, int point, String content) {
        this.id = id;
        this.student = student;
        this.subject = subject;
        this.point = point;
        this.studentName = student.getUser().getName();
        this.studentAvatar = student.getUser().getAvatar();
        this.content = content;
        this.creationTime = new Date().toString();
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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
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
}
