package de.ait.timepad.controllers;

import de.ait.timepad.models.User;
import de.ait.timepad.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api")
public class UsersController {

    private final UsersService usersService;



    @PostMapping("/users")
    public User addUser(@RequestBody User user) {


    }
}
