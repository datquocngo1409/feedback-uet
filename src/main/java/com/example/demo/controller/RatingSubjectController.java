package com.example.demo.controller;

import com.example.demo.model.RatingSubject;
import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import com.example.demo.model.Teacher;
import com.example.demo.model.dto.RatingSubjectDto;
import com.example.demo.model.dto.RatingSubjectRequestDto;
import com.example.demo.model.dto.TeacherDto;
import com.example.demo.service.RatingSubjectService;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RatingSubjectController {
    @Autowired
    public RatingSubjectService ratingSubjectService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;

    //API trả về List Rating Subject.
    @RequestMapping(value = "/rating-subject", method = RequestMethod.GET)
    public ResponseEntity<List<RatingSubjectDto>> listAllRatingSubjects() {
        List<RatingSubject> listObject = ratingSubjectService.findAll();
        if (listObject.isEmpty()) {
            return new ResponseEntity<List<RatingSubjectDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<RatingSubjectDto> ratingSubjectDtos = new ArrayList<>();
        for (RatingSubject ratingSubject : listObject) {
            RatingSubjectDto ratingSubjectDto = new RatingSubjectDto(ratingSubject);
            ratingSubjectDtos.add(ratingSubjectDto);
        }
        return new ResponseEntity<List<RatingSubjectDto>>(ratingSubjectDtos, HttpStatus.OK);
    }

    //API trả về RatingSubject có ID trên url.
    @RequestMapping(value = "/rating-subject/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RatingSubjectDto> getRatingSubjectById(@PathVariable("id") Long id) {
        RatingSubject object = ratingSubjectService.findById(id);
        if (object == null) {
            System.out.println("RatingSubject not found");
            return new ResponseEntity<RatingSubjectDto>(HttpStatus.NOT_FOUND);
        }
        RatingSubjectDto ratingSubjectDtos = new RatingSubjectDto(object);
        return new ResponseEntity<RatingSubjectDto>(ratingSubjectDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/rating-subject/by-subject/{subjectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RatingSubjectDto>> getRatingSubjectBySubjectId(@PathVariable("subjectId") Long subjectId) {
        Subject subject = subjectService.findById(subjectId);
        List<RatingSubject> object = ratingSubjectService.findAllBySubject(subject);
        if (object == null) {
            System.out.println("RatingSubject not found");
            return new ResponseEntity<List<RatingSubjectDto>>(HttpStatus.NOT_FOUND);
        }
        List<RatingSubjectDto> ratingSubjectDtoList = new ArrayList<>();
        for (RatingSubject ratingSubject : object) {
            RatingSubjectDto dto = new RatingSubjectDto(ratingSubject);
            ratingSubjectDtoList.add(dto);
        }
        return new ResponseEntity<List<RatingSubjectDto>>(ratingSubjectDtoList, HttpStatus.OK);
    }

    //rate
    @RequestMapping(value = "/rating-subject/rate", method = RequestMethod.POST)
    public ResponseEntity<Void> createRatingSubject(@RequestBody RatingSubjectRequestDto ratingValue, UriComponentsBuilder ucBuilder) {
        RatingSubject object = new RatingSubject();
        Student student = studentService.findById(ratingValue.getStudentId());
        Subject subject = subjectService.findById(ratingValue.getSubjectId());
        object.setStudent(student);
        object.setSubject(subject);
        object.setPoint(ratingValue.getPoint());
        object.setStudentName(student.getUser().getName());
        object.setStudentAvatar(student.getUser().getAvatar());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        object.setCreationTime(dtf.format(now));
        ratingSubjectService.rate(object);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/rating-subject/{id}").buildAndExpand(object.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //API xóa một Admin với ID trên url.
    @RequestMapping(value = "/rating-subject/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<RatingSubject> deleteRatingSubject(@PathVariable("id") Long id) {
        RatingSubject object = ratingSubjectService.findById(id);
        if (object == null) {
            System.out.println("Unable to delete. RatingSubject not found");
            return new ResponseEntity<RatingSubject>(HttpStatus.NOT_FOUND);
        }

        ratingSubjectService.delete(id);
        return new ResponseEntity<RatingSubject>(HttpStatus.NO_CONTENT);
    }

}
