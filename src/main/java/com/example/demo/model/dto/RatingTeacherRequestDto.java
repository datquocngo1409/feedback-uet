package com.example.demo.model.dto;

import lombok.Data;

@Data
public class RatingTeacherRequestDto {
    private Long id;
    private Long studentId;
    private Long teacherId;
    private Integer point;

    public RatingTeacherRequestDto() {
    }

    public RatingTeacherRequestDto(Long id, Long studentId, Long teacherId, Integer point) {
        this.id = id;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.point = point;
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

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
