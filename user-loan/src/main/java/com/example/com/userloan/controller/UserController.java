package com.example.com.userloan.controller;

import com.example.com.userloan.datalayer.entities.Users;
import com.example.com.userloan.models.Response;
import com.example.com.userloan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    private Response createUser(@RequestBody Users users) {
        return userService.createUser(users);
    }

    @GetMapping("/users/search")
    private Response searchUser(@PathParam("nationalId") String nationalId) {
        return userService.searchUser(nationalId);
    }

}
