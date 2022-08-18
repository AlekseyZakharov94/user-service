package com.alzakhar.user_service.controllers;

import com.alzakhar.user_service.dto.ChangeStatusDto;
import com.alzakhar.user_service.model.User;
import com.alzakhar.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/addUser")
    public Long addUser(@Valid User user) {
        return userService.saveNewUser(user);
    }

    @GetMapping("/getUser")
    public User getUserById(@RequestParam Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping(value = "/changeStatus")
    public ChangeStatusDto changeStatus(@Valid @RequestBody ChangeStatusDto changeStatusRequest) {
        return userService.changeStatus(changeStatusRequest);
    }
}
