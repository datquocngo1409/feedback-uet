package com.example.demo.controller;
import com.example.demo.model.*;
import com.example.demo.model.dto.RatingTeacherDto;
import com.example.demo.model.dto.RatingTeacherRequestDto;
import com.example.demo.service.RatingTeacherService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RatingTeacherController {
    @Autowired
    public RatingTeacherService ratingTeacherService;
    @Autowired
    public TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    //API trả về List Rating Teacher.
    @RequestMapping(value = "/rating-teacher", method = RequestMethod.GET)
    public ResponseEntity<List<RatingTeacherDto>> listAllRatingTeachers() {
        List<RatingTeacher> listObject = ratingTeacherService.findAll();
        if (listObject.isEmpty()) {
            return new ResponseEntity<List<RatingTeacherDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<RatingTeacherDto> ratingTeacherDtos = new ArrayList<>();
        for (RatingTeacher ratingTeacher : listObject) {
            RatingTeacherDto ratingTeacherDto = new RatingTeacherDto(ratingTeacher);
            ratingTeacherDtos.add(ratingTeacherDto);
        }
        return new ResponseEntity<List<RatingTeacherDto>>(ratingTeacherDtos, HttpStatus.OK);
    }

    //API trả về RatingTeacher có ID trên url.
    @RequestMapping(value = "/rating-teacher/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RatingTeacherDto> getRatingTeacherById(@PathVariable("id") Long id) {
        RatingTeacher object = ratingTeacherService.findById(id);
        if (object == null) {
            System.out.println("RatingTeacher not found");
            return new ResponseEntity<RatingTeacherDto>(HttpStatus.NOT_FOUND);
        }
        RatingTeacherDto ratingTeacherDtos = new RatingTeacherDto(object);
        return new ResponseEntity<RatingTeacherDto>(ratingTeacherDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/rating-teacher/by-teacher/{teacherId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RatingTeacherDto>> getRatingTeacherByTeacherId(@PathVariable("teacherId") Long teacherId) {
        Teacher teacher = teacherService.findById(teacherId);
        List<RatingTeacher> object = ratingTeacherService.findAllByTeacher(teacher);
        if (object == null) {
            System.out.println("RatingTeacher not found");
            return new ResponseEntity<List<RatingTeacherDto>>(HttpStatus.NOT_FOUND);
        }
        List<RatingTeacherDto> ratingTeacherDtoList = new ArrayList<>();
        for (RatingTeacher ratingTeacher : object) {
            RatingTeacherDto dto = new RatingTeacherDto(ratingTeacher);
            ratingTeacherDtoList.add(dto);
        }
        return new ResponseEntity<List<RatingTeacherDto>>(ratingTeacherDtoList, HttpStatus.OK);
    }

    //rate
    @RequestMapping(value = "/rating-teacher/rate", method = RequestMethod.POST)
    public ResponseEntity<Void> createRatingTeacher(@RequestBody RatingTeacherRequestDto ratingValue, UriComponentsBuilder ucBuilder) {
        RatingTeacher object = new RatingTeacher();
        Student student = studentService.findById(ratingValue.getStudentId());
        Teacher teacher = teacherService.findById(ratingValue.getTeacherId());
        object.setStudent(student);
        object.setTeacher(teacher);
        object.setPoint(ratingValue.getPoint());
        object.setStudentName(student.getUser().getName());
        object.setStudentAvatar(student.getUser().getAvatar());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        object.setCreationTime(dtf.format(now));
        ratingTeacherService.rate(object);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/rating-teacher/{id}").buildAndExpand(object.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //API xóa một Admin với ID trên url.
    @RequestMapping(value = "/rating-teacher/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<RatingTeacher> RatingTeacher(@PathVariable("id") Long id) {

        RatingTeacher object = ratingTeacherService.findById(id);
        if (object == null) {
            System.out.println("Unable to delete. RatingTeacher not found");
            return new ResponseEntity<RatingTeacher>(HttpStatus.NOT_FOUND);
        }

        ratingTeacherService.delete(id);
        return new ResponseEntity<RatingTeacher>(HttpStatus.NO_CONTENT);
    }
}
