package com.example.demo.model.dto;

import com.example.demo.model.RatingTeacher;
import lombok.Data;

@Data
public class RatingTeacherDto {
    private Long id;

    private Long studentId;

    private Long teacherId;

    private int point;

    private String studentName;

    private String studentAvatar;

    private String content;

    private String creationTime;

    public RatingTeacherDto() {
    }

    public RatingTeacherDto(RatingTeacher ratingTeacher) {
        this.id = ratingTeacher.getId();
        this.studentId = ratingTeacher.getStudent().getId();
        this.teacherId = ratingTeacher.getTeacher().getId();
        this.point = ratingTeacher.getPoint();
        this.studentName = ratingTeacher.getStudentName();
        this.studentAvatar = ratingTeacher.getStudentAvatar();
        this.content = ratingTeacher.getContent();
        this.creationTime = ratingTeacher.getCreationTime();
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

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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
