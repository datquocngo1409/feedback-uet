package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import com.example.demo.model.Teacher;
import com.example.demo.model.dto.CommentDto;
import com.example.demo.model.dto.CommentRequestDto;
import com.example.demo.service.CommentService;
import com.example.demo.service.StudentService;
import com.example.demo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    public CommentService commentService;
    @Autowired
    public StudentService studentService;
    @Autowired
    public SubjectService subjectService;

    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public ResponseEntity<List<CommentDto>> listAllComments() {
        List<Comment> accounts = commentService.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<CommentDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : accounts) {
            CommentDto commentDto = new CommentDto(comment);
            commentDtos.add(commentDto);
        }
        return new ResponseEntity<List<CommentDto>>(commentDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("id") Long id) {
        System.out.println("Fetching Comment with id " + id);
        Comment account = commentService.findById(id);
        if (account == null) {
            System.out.println("Comment with id " + id + " not found");
            return new ResponseEntity<CommentDto>(HttpStatus.NOT_FOUND);
        }
        CommentDto commentDto = new CommentDto(account);
        return new ResponseEntity<CommentDto>(commentDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/bySubjectId/{subjectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentDto>> getCommentBySubjectId(@PathVariable("subjectId") Long subjectId) {
        Subject subject = subjectService.findById(subjectId);
        List<Comment> accounts = commentService.findAllBySubject(subject);
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<CommentDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : accounts) {
            CommentDto commentDto = new CommentDto(comment);
            commentDtos.add(commentDto);
        }
        return new ResponseEntity<List<CommentDto>>(commentDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/rep/{commentId}", method = RequestMethod.POST)
    public ResponseEntity<Void> createCommentRep(@RequestBody CommentRequestDto commentRequestDto, UriComponentsBuilder ucBuilder, @PathVariable("commentId") Long commentId) {
        HttpHeaders headers = new HttpHeaders();
        Comment parentComment = commentService.findById(commentId);
        if (parentComment != null) {
            Comment comment = new Comment(commentRequestDto);
            Student student = studentService.findById(commentRequestDto.getStudentId());
            Subject subject = subjectService.findById(commentRequestDto.getSubjectId());
            comment.setSubject(subject);
            comment.setStudent(student);
            comment.setReply(true);
            commentService.update(comment);
            String listReplyId = (parentComment.getReplyCommentId() != null)? parentComment.getReplyCommentId() : "";
            listReplyId = listReplyId + ", " + comment.getId();
            parentComment.setReplyCommentId(listReplyId);
            headers.setLocation(ucBuilder.path("/comment/{id}").buildAndExpand(comment.getId()).toUri());
        }
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResponseEntity<Void> createComment(@RequestBody CommentRequestDto commentRequestDto, UriComponentsBuilder ucBuilder) {
        Comment comment = new Comment(commentRequestDto);
        Student student = studentService.findById(commentRequestDto.getStudentId());
        Subject subject = subjectService.findById(commentRequestDto.getSubjectId());
        comment.setSubject(subject);
        comment.setStudent(student);
        commentService.update(comment);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/comment/{id}").buildAndExpand(comment.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<CommentDto> updateAdmin(@PathVariable("id") Long id, @RequestBody CommentRequestDto commentRequestDto) {
        System.out.println("Updating Comment " + id);

        Comment curremComment = commentService.findById(id);

        if (curremComment == null) {
            System.out.println("Comment with id " + id + " not found");
            return new ResponseEntity<CommentDto>(HttpStatus.NOT_FOUND);
        }

        Comment comment = new Comment(commentRequestDto);
        Student student = studentService.findById(commentRequestDto.getStudentId());
        Subject subject = subjectService.findById(commentRequestDto.getSubjectId());
        comment.setSubject(subject);
        comment.setStudent(student);
        commentService.update(comment);

        curremComment = comment;

        commentService.update(curremComment);
        CommentDto commentDto = new CommentDto(curremComment);
        return new ResponseEntity<CommentDto>(commentDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Comment> deleteComment(@PathVariable("id") Long id) {

        Comment comment = commentService.findById(id);
        if (comment == null) {
            System.out.println("Unable to delete. Comment with id " + id + " not found");
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        }

        commentService.delete(id);
        return new ResponseEntity<Comment>(HttpStatus.NO_CONTENT);
    }
}
