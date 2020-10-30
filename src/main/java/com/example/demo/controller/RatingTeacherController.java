package com.example.demo.controller;
import com.example.demo.model.RatingTeacher;
import com.example.demo.model.dto.RatingTeacherDto;
import com.example.demo.service.RatingTeacherService;
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
public class RatingTeacherController {
    @Autowired
    public RatingTeacherService ratingTeacherService;

    //API trả về List Rating Subject.
    @RequestMapping(value = "/rating-teacher", method = RequestMethod.GET)
    public ResponseEntity<List<RatingTeacherDto>> listAllRatingTeachers() {
        List<RatingTeacher> listObject = ratingTeacherService.findAll();
        if (listObject.isEmpty()) {
            return new ResponseEntity<List<RatingTeacherDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<RatingTeacherDto> ratingSubjectDtos = new ArrayList<>();
        for (RatingTeacher ratingSubject : listObject) {
            RatingTeacherDto ratingSubjectDto = new RatingTeacherDto(ratingSubject);
            ratingSubjectDtos.add(ratingSubjectDto);
        }
        return new ResponseEntity<List<RatingTeacherDto>>(ratingSubjectDtos, HttpStatus.OK);
    }

    //API trả về RatingTeacher có ID trên url.
    @RequestMapping(value = "/rating-teacher/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RatingTeacherDto> getRatingTeacherById(@PathVariable("id") Long id) {
        System.out.println("Fetching RatingTeacher with id " + id);
        RatingTeacher object = ratingTeacherService.findById(id);
        if (object == null) {
            System.out.println("RatingTeacher with id " + id + " not found");
            return new ResponseEntity<RatingTeacherDto>(HttpStatus.NOT_FOUND);
        }
        RatingTeacherDto ratingSubjectDtos = new RatingTeacherDto(object);
        return new ResponseEntity<RatingTeacherDto>(ratingSubjectDtos, HttpStatus.OK);
    }

    //rate
    @RequestMapping(value = "/rating-teacher/rate", method = RequestMethod.POST)
    public ResponseEntity<Void> createRatingTeacher(@RequestBody RatingTeacher object, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating RatingTeacher " + object.getId());
        ratingTeacherService.rate(object);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/rating-teacher/{id}").buildAndExpand(object.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //API xóa một Admin với ID trên url.
    @RequestMapping(value = "/rating-teacher/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<RatingTeacher> RatingTeacher(@PathVariable("id") Long id) {
        System.out.println("Fetching & Deleting RatingTeacher with id " + id);

        RatingTeacher object = ratingTeacherService.findById(id);
        if (object == null) {
            System.out.println("Unable to delete. RatingTeacher with id " + id + " not found");
            return new ResponseEntity<RatingTeacher>(HttpStatus.NOT_FOUND);
        }

        ratingTeacherService.delete(id);
        return new ResponseEntity<RatingTeacher>(HttpStatus.NO_CONTENT);
    }
}
