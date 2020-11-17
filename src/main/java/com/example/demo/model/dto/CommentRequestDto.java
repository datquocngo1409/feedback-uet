package com.example.demo.model.dto;

import lombok.Data;

@Data
public class CommentRequestDto {
    private Long id;

    private Long studentId;

    private Long subjectId;

    private String content;

    private boolean isReply;

    private String replyCommentId;

    public CommentRequestDto() {
    }

    public CommentRequestDto(Long studentId, Long subjectId, String content, boolean isReply, String replyCommentId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.content = content;
        this.isReply = isReply;
        this.replyCommentId = replyCommentId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isReply() {
        return isReply;
    }

    public void setReply(boolean reply) {
        isReply = reply;
    }

    public String getReplyCommentId() {
        return replyCommentId;
    }

    public void setReplyCommentId(String replyCommentId) {
        this.replyCommentId = replyCommentId;
    }
}
