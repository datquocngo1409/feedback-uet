package com.example.demo.model.dto;

import com.example.demo.model.RatingSubject;
import lombok.Data;

@Data
public class RatingSubjectDto {
    private Long id;

    private Long studentId;

    private Long subjectId;

    private int point;

    private String studentName;

    private String studentAvatar;

    private String content;

    private String creationTime;

    public RatingSubjectDto() {
    }

    public RatingSubjectDto(RatingSubject ratingSubject) {
        this.id = ratingSubject.getId();
        this.studentId = ratingSubject.getStudent().getId();
        this.subjectId = ratingSubject.getSubject().getId();
        this.point = ratingSubject.getPoint();
        this.studentName = ratingSubject.getStudentName();
        this.studentAvatar = ratingSubject.getStudentAvatar();
        this.content = ratingSubject.getContent();
        this.creationTime = ratingSubject.getCreationTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
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
