package com.example.demo.model;

import com.example.demo.model.dto.SubjectDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String code;

    @NotNull
    private String name;

    private double rating = 0;

    private int ratingCount = 0;

    private String startDate;

    @ManyToOne
    private Teacher teacher;

    public Subject() {
    }

    public Subject(Long id, @NotNull String code, @NotNull String name, double rating, int ratingCount, String startDate, Teacher teacher, List<Student> studentList) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.startDate = startDate;
        this.teacher = teacher;
    }

    public Subject(@NotNull String code, @NotNull String name, double rating, int ratingCount, String startDate, Teacher teacher, List<Student> studentList) {
        this.code = code;
        this.name = name;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.startDate = startDate;
        this.teacher = teacher;
    }

    public Subject(SubjectDto dto) {
        this.id = dto.getId();
        this.code = dto.getCode();
        this.name = dto.getName();
        this.rating = dto.getRating();
        this.ratingCount = dto.getRatingCount();
        this.startDate = dto.getStartDate();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
