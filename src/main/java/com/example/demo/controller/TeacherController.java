package com.example.demo.controller;

import com.example.demo.model.Teacher;
import com.example.demo.model.User;
import com.example.demo.model.dto.TeacherDto;
import com.example.demo.service.TeacherService;
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
public class TeacherController {
    @Autowired
    public TeacherService teacherService;
    @Autowired
    private UserService userService;

    //API trả về List Teacher.
    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public ResponseEntity<List<TeacherDto>> listAllTeachers() {
        List<Teacher> listObject = teacherService.findAll();
        if (listObject.isEmpty()) {
            return new ResponseEntity<List<TeacherDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<TeacherDto> teacherDtos = new ArrayList<>();
        for (Teacher teacher : listObject) {
            TeacherDto teacherDto = new TeacherDto(teacher);
            teacherDtos.add(teacherDto);
        }
        return new ResponseEntity<List<TeacherDto>>(teacherDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/teacher/full", method = RequestMethod.GET)
    public ResponseEntity<List<Teacher>> listAllTeachersFull() {
        List<Teacher> listObject = teacherService.findAll();
        if (listObject.isEmpty()) {
            return new ResponseEntity<List<Teacher>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Teacher>>(listObject, HttpStatus.OK);
    }

    //API trả về Teacher có ID trên url.
    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable("id") Long id) {
        System.out.println("Fetching Teacher with id " + id);
        Teacher object = teacherService.findById(id);
        if (object == null) {
            System.out.println("Teacher with id " + id + " not found");
            return new ResponseEntity<TeacherDto>(HttpStatus.NOT_FOUND);
        }
        TeacherDto teacherDto = new TeacherDto(object);
        return new ResponseEntity<TeacherDto>(teacherDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/teacher/full/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teacher> getTeacherByIdFull(@PathVariable("id") Long id) {
        System.out.println("Fetching Teacher with id " + id);
        Teacher object = teacherService.findById(id);
        if (object == null) {
            System.out.println("Teacher with id " + id + " not found");
            return new ResponseEntity<Teacher>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Teacher>(object, HttpStatus.OK);
    }

    @RequestMapping(value = "/teacher/by-user/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDto> getTeacherByUserId(@PathVariable("userId") Long userId) {
        User user = userService.findById(userId);
        Teacher object = teacherService.findByUser(user);
        System.out.println("Fetching Teacher with id " + object.getId());
        if (object == null) {
            System.out.println("Teacher with id " + object.getId() + " not found");
            return new ResponseEntity<TeacherDto>(HttpStatus.NOT_FOUND);
        }
        TeacherDto teacherDto = new TeacherDto(object);
        return new ResponseEntity<TeacherDto>(teacherDto, HttpStatus.OK);
    }

    //API tạo một Admin mới.
    @RequestMapping(value = "/teacher", method = RequestMethod.POST)
    public ResponseEntity<Void> createTeacher(@RequestBody Teacher object, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Teacher " + object.getUser().getName());
        teacherService.update(object);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/teacher/{id}").buildAndExpand(object.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //API cập nhật một Admin với ID trên url.
    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<TeacherDto> updateAdmin(@PathVariable("id") Long id, @RequestBody Teacher object) {
        System.out.println("Updating Teacher " + id);

        Teacher current = teacherService.findById(id);

        if (current == null) {
            System.out.println("Teacher with id " + id + " not found");
            return new ResponseEntity<TeacherDto>(HttpStatus.NOT_FOUND);
        }

        current = object;

        teacherService.update(current);
        TeacherDto teacherDto = new TeacherDto(current);
        return new ResponseEntity<TeacherDto>(teacherDto, HttpStatus.OK);
    }

    //API xóa một Admin với ID trên url.
    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable("id") Long id) {
        System.out.println("Fetching & Deleting Teacher with id " + id);

        Teacher object = teacherService.findById(id);
        if (object == null) {
            System.out.println("Unable to delete. Teacher with id " + id + " not found");
            return new ResponseEntity<Teacher>(HttpStatus.NOT_FOUND);
        }

        teacherService.delete(id);
        return new ResponseEntity<Teacher>(HttpStatus.NO_CONTENT);
    }
}
