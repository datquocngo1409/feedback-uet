package com.example.demo.model.dto;

import com.example.demo.model.Comment;
import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;

    private Long studentId;

    private Long subjectId;

    private String studentName;

    private String studentAvatar;

    private String content;

    private String creationTime;

    private boolean isReply;

    private String replyCommentId;

    private int likeNumber;

    public CommentDto() {
    }

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.studentId = comment.getStudent().getId();
        this.subjectId = comment.getSubject().getId();
        this.studentName = comment.getStudentName();
        this.studentAvatar = comment.getStudentAvatar();
        this.content = comment.getContent();
        this.creationTime = comment.getCreationTime();
        this.isReply = comment.isReply();
        this.replyCommentId = comment.getReplyCommentId();
        this.likeNumber = comment.getLikeNumber();
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentAvatar() {
        return studentAvatar;
    }

    public void setStudentAvatar(String studentAvatar) {
        this.studentAvatar = studentAvatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
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

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }
}
