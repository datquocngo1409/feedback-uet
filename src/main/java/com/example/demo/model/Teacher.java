package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_Teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ratingCount = 0;

    private double rating = 0;

    @OneToOne
    private User user;

    public Teacher() {
    }

    public Teacher(User user) {
        this.ratingCount = 0;
        this.rating = 0;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
