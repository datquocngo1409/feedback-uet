package com.example.demo.model.dto;

import com.example.demo.model.Student;
import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private Long userId;
    private int mssv;

    public StudentDto() {
    }

    public StudentDto(Student student) {
        this.id = student.getId();
        this.userId = student.getUser().getId();
        this.mssv = student.getMssv();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getMssv() {
        return mssv;
    }

    public void setMssv(int mssv) {
        this.mssv = mssv;
    }
}
