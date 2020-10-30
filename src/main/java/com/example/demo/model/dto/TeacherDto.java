package com.example.demo.model.dto;

import com.example.demo.model.Teacher;
import lombok.Data;

@Data
public class TeacherDto {
    private Long id;
    private int ratingCount;
    private double rating;
    private Long userId;

    public TeacherDto() {
    }

    public TeacherDto(Teacher teacher) {
        this.id = teacher.getId();
        this.rating = teacher.getRating();
        this.ratingCount = teacher.getRatingCount();
        this.userId = teacher.getUser().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
