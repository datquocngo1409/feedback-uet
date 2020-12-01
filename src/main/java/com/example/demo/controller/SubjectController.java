package com.example.demo.controller;

import com.example.demo.model.Subject;
import com.example.demo.model.Teacher;
import com.example.demo.model.dto.SubjectDto;
import com.example.demo.service.SubjectService;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SubjectController {
    @Autowired
    public SubjectService subjectService;

    @Autowired
    public TeacherService teacherService;

    //API trả về List Subject.
    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    public ResponseEntity<List<SubjectDto>> listAllSubjects() {
        List<Subject> listObject = subjectService.findAll();
        if (listObject.isEmpty()) {
            return new ResponseEntity<List<SubjectDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<SubjectDto> subjectDtos = new ArrayList<>();
        for (Subject subject : listObject) {
            SubjectDto subjectDto = new SubjectDto(subject);
            subjectDtos.add(subjectDto);
        }
        return new ResponseEntity<List<SubjectDto>>(subjectDtos, HttpStatus.OK);
    }

    //API trả về Subject có ID trên url.
    @RequestMapping(value = "/subject/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable("id") Long id) {
        System.out.println("Fetching Subject with id " + id);
        Subject object = subjectService.findById(id);
        if (object == null) {
            System.out.println("Subject with id " + id + " not found");
            return new ResponseEntity<SubjectDto>(HttpStatus.NOT_FOUND);
        }
        SubjectDto subjectDto = new SubjectDto(object);
        return new ResponseEntity<SubjectDto>(subjectDto, HttpStatus.OK);
    }

    //API tạo một Admin mới.
    @RequestMapping(value = "/subject", method = RequestMethod.POST)
    public ResponseEntity<Void> createSubject(@RequestBody Subject object, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Subject " + object.getCode());
        subjectService.update(object);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/subject/{id}").buildAndExpand(object.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //API cập nhật một Admin với ID trên url.
    @RequestMapping(value = "/subject/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<SubjectDto> updateAdmin(@PathVariable("id") Long id, @RequestBody SubjectDto subjectDto) {
        System.out.println("Updating Subject " + id);

        Subject current = subjectService.findById(id);

        if (current == null) {
            System.out.println("Subject with id " + id + " not found");
            return new ResponseEntity<SubjectDto>(HttpStatus.NOT_FOUND);
        }

        Teacher teacher = teacherService.findById(subjectDto.getTeacherId());
        current = new Subject(subjectDto);
        current.setTeacher(teacher);

        subjectService.update(current);
        SubjectDto result = new SubjectDto(current);
        return new ResponseEntity<SubjectDto>(result, HttpStatus.OK);
    }

    //API xóa một Admin với ID trên url.
    @RequestMapping(value = "/subject/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Subject> deleteSubject(@PathVariable("id") Long id) {
        System.out.println("Fetching & Deleting Subject with id " + id);

        Subject object = subjectService.findById(id);
        if (object == null) {
            System.out.println("Unable to delete. Subject with id " + id + " not found");
            return new ResponseEntity<Subject>(HttpStatus.NOT_FOUND);
        }

        System.out.println(object.toString());
        subjectService.delete(id);
        return new ResponseEntity<Subject>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/subject/updateDate/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<SubjectDto> updateDate(@PathVariable("id") Long id, @RequestBody Map<String, String> parameters) {
        String startDate = parameters.get("startDate");
        System.out.println(startDate);
        System.out.println("Updating Subject " + id);

        Subject current = subjectService.findById(id);

        if (current == null) {
            System.out.println("Subject with id " + id + " not found");
            return new ResponseEntity<SubjectDto>(HttpStatus.NOT_FOUND);
        }

        current.setStartDate(startDate);

        subjectService.update(current);
        SubjectDto subjectDto = new SubjectDto(current);
        return new ResponseEntity<SubjectDto>(subjectDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/subject/updateTeacher/{id}/{teacherId}", method = RequestMethod.PATCH)
    public ResponseEntity<SubjectDto> updateTeacher(@PathVariable("id") Long id, @PathVariable("teacherId") Long teacherId) {
        System.out.println("Updating Subject " + id);

        Subject current = subjectService.findById(id);

        if (current == null) {
            System.out.println("Subject with id " + id + " not found");
            return new ResponseEntity<SubjectDto>(HttpStatus.NOT_FOUND);
        }

        Teacher teacher = teacherService.findById(teacherId);
        current.setTeacher(teacher);

        subjectService.update(current);
        SubjectDto subjectDto = new SubjectDto(current);
        return new ResponseEntity<SubjectDto>(subjectDto, HttpStatus.OK);
    }

}
