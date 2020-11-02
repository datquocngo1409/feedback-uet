package com.example.demo.model.dto;

import com.example.demo.model.Subject;
import lombok.Data;

@Data
public class SubjectDto {
    private Long id;

    private String code;

    private String name;

    private double rating = 0;

    private int ratingCount = 0;

    private Long teacherId;

    private String teacherName;

    private String startDate;

    public SubjectDto() {
    }

    public SubjectDto(Subject subject) {
        this.id = subject.getId();
        this.code = subject.getCode();
        this.name = subject.getName();
        this.rating = subject.getRating();
        this.ratingCount = subject.getRatingCount();
        this.startDate = subject.getStartDate();
        if (subject.getTeacher() != null) {
            this.teacherId = subject.getTeacher().getId();
            this.teacherName = subject.getTeacher().getUser().getName();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
