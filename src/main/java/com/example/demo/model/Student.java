package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mssv = 0;

    @OneToOne
    private User user;

    public Student() {
    }

    public Student(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getMssv() {
        return mssv;
    }

    public void setMssv(int mssv) {
        this.mssv = mssv;
    }
}
