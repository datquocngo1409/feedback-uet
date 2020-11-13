package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.model.User;
import com.example.demo.model.dto.StudentDto;
import com.example.demo.service.StudentService;
import com.example.demo.service.UserService;
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
public class StudentController {
    @Autowired
    public StudentService studentService;
    @Autowired
    public UserService userService;

    //API trả về List Student.
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ResponseEntity<List<StudentDto>> listAllTeachers() {
        List<Student> listObject = studentService.findAll();
        if (listObject.isEmpty()) {
            return new ResponseEntity<List<StudentDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : listObject) {
            StudentDto studentDto = new StudentDto(student);
            studentDtos.add(studentDto);
        }
        return new ResponseEntity<List<StudentDto>>(studentDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/student/full", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getAllFull() {
        List<Student> listObject = studentService.findAll();
        if (listObject.isEmpty()) {
            return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Student>>(listObject, HttpStatus.OK);
    }

    //API trả về Student có ID trên url.
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long id) {
        System.out.println("Fetching Student with id " + id);
        Student object = studentService.findById(id);
        if (object == null) {
            System.out.println("Student with id " + id + " not found");
            return new ResponseEntity<StudentDto>(HttpStatus.NOT_FOUND);
        }
        StudentDto studentDto = new StudentDto(object);
        return new ResponseEntity<StudentDto>(studentDto, HttpStatus.OK);
    }

    //API trả về Student có ID trên url.
    @RequestMapping(value = "/student/full/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudentByIdFull(@PathVariable("id") Long id) {
        System.out.println("Fetching Student with id " + id);
        Student object = studentService.findById(id);
        if (object == null) {
            System.out.println("Student with id " + id + " not found");
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Student>(object, HttpStatus.OK);
    }

    @RequestMapping(value = "/student/by-user/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDto> getStudentByUserId(@PathVariable("userId") Long userId) {
        User user = userService.findById(userId);
        Student object = studentService.findByUser(user);
        System.out.println("Fetching Student with id " + object.getId());
        if (object == null) {
            System.out.println("Student with id " + object.getId() + " not found");
            return new ResponseEntity<StudentDto>(HttpStatus.NOT_FOUND);
        }
        StudentDto studentDto = new StudentDto(object);
        return new ResponseEntity<StudentDto>(studentDto, HttpStatus.OK);
    }

    //API tạo một Admin mới.
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public ResponseEntity<Void> createStudent(@RequestBody Student object, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Student " + object.getUser().getName());
        studentService.update(object);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/student/{id}").buildAndExpand(object.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //API cập nhật một Admin với ID trên url.
    @RequestMapping(value = "/student/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<StudentDto> updateAdmin(@PathVariable("id") Long id, @RequestBody Student object) {
        System.out.println("Updating Student " + id);

        Student current = studentService.findById(id);

        if (current == null) {
            System.out.println("Student with id " + id + " not found");
            return new ResponseEntity<StudentDto>(HttpStatus.NOT_FOUND);
        }

        current = object;

        studentService.update(current);
        StudentDto studentDto = new StudentDto(current);
        return new ResponseEntity<StudentDto>(studentDto, HttpStatus.OK);
    }

    //API xóa một Admin với ID trên url.
    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Long id) {
        System.out.println("Fetching & Deleting Student with id " + id);

        Student object = studentService.findById(id);
        if (object == null) {
            System.out.println("Unable to delete. Student with id " + id + " not found");
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }

        studentService.delete(id);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }

}
