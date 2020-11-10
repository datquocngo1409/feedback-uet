package com.example.demo.model.dto;

import lombok.Data;

@Data
public class RatingSubjectRequestDto {
    private Long id;
    private Long studentId;
    private Long subjectId;
    private Integer point;

    public RatingSubjectRequestDto() {
    }

    public RatingSubjectRequestDto(Long studentId, Long subjectId, Integer point) {
        this.studentId = studentId;
        this.subjectId = subjectId;
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

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
