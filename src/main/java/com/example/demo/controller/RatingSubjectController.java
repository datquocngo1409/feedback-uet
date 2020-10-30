package com.example.demo.controller;

import com.example.demo.model.RatingSubject;
import com.example.demo.model.Teacher;
import com.example.demo.model.dto.RatingSubjectDto;
import com.example.demo.model.dto.TeacherDto;
import com.example.demo.service.RatingSubjectService;
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
public class RatingSubjectController {
    @Autowired
    public RatingSubjectService ratingSubjectService;

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
        System.out.println("Fetching RatingSubject with id " + id);
        RatingSubject object = ratingSubjectService.findById(id);
        if (object == null) {
            System.out.println("RatingSubject with id " + id + " not found");
            return new ResponseEntity<RatingSubjectDto>(HttpStatus.NOT_FOUND);
        }
        RatingSubjectDto ratingSubjectDtos = new RatingSubjectDto(object);
        return new ResponseEntity<RatingSubjectDto>(ratingSubjectDtos, HttpStatus.OK);
    }

    //rate
    @RequestMapping(value = "/rating-subject/rate", method = RequestMethod.POST)
    public ResponseEntity<Void> createRatingSubject(@RequestBody RatingSubject object, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating RatingSubject " + object.getId());
        ratingSubjectService.rate(object);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/rating-subject/{id}").buildAndExpand(object.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //API xóa một Admin với ID trên url.
    @RequestMapping(value = "/rating-subject/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<RatingSubject> deleteRatingSubject(@PathVariable("id") Long id) {
        System.out.println("Fetching & Deleting RatingSubject with id " + id);

        RatingSubject object = ratingSubjectService.findById(id);
        if (object == null) {
            System.out.println("Unable to delete. RatingSubject with id " + id + " not found");
            return new ResponseEntity<RatingSubject>(HttpStatus.NOT_FOUND);
        }

        ratingSubjectService.delete(id);
        return new ResponseEntity<RatingSubject>(HttpStatus.NO_CONTENT);
    }

}
