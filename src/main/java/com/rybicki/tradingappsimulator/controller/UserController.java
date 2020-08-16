package com.rybicki.tradingappsimulator.controller;

import com.rybicki.tradingappsimulator.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/user/create/{numberOfUsers}")
    public void createUsers(@PathVariable Integer numberOfUsers) {
        userService.initUsers(numberOfUsers);
    }

    @DeleteMapping(value = "/user/delete")
    public void deleteUsers() {
        userService.deleteUsers();
    }
}
