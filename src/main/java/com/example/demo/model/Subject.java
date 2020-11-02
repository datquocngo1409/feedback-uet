package com.example.demo.model;

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

    @ManyToMany
    private List<Student> studentList;

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
        this.studentList = studentList;
    }

    public Subject(@NotNull String code, @NotNull String name, double rating, int ratingCount, String startDate, Teacher teacher, List<Student> studentList) {
        this.code = code;
        this.name = name;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.startDate = startDate;
        this.teacher = teacher;
        this.studentList = studentList;
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

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
