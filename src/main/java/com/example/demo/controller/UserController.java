package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class UserController {
    @Autowired
    public UserService userService;

    //API trả về List User.
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> listAllUsers() {
        List<User> accounts = userService.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<UserDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : accounts) {
            UserDto userDto = new UserDto(user);
            userDtos.add(userDto);
        }
        return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
    }

    //API trả về User có ID trên url.
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        System.out.println("Fetching User with id " + id);
        User account = userService.findById(id);
        if (account == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
        UserDto userDto = new UserDto(account);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    //API tạo một Admin mới.
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getName());
        userService.updateUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //API cập nhật một Admin với ID trên url.
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<UserDto> updateAdmin(@PathVariable("id") Long id, @RequestBody User user) {
        System.out.println("Updating User " + id);

        User curremUser = userService.findById(id);

        if (curremUser == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }

        curremUser = user;

        userService.updateUser(curremUser);
        UserDto userDto = new UserDto(curremUser);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    //API xóa một Admin với ID trên url.
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        System.out.println("Fetching & Deleting User with id " + id);

        User user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userService.deleteUser(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/user/getByToken", method = RequestMethod.PATCH)
    public ResponseEntity<UserDto> getUserByToken(@RequestParam Map<String, String> parameters) {
        String token = parameters.get("token");
        User user = userService.findByToken(token);
        if (user == null) {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        } else {
            UserDto userDto = new UserDto(user);
            return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
        }
    }
}
