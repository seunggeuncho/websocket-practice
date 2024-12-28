package com.practice.socket.User.controller;

import com.practice.socket.User.model.UserDto;
import com.practice.socket.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.practice.socket.User.model.User;
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam UserDto userDto, Model model){
        User user = userService.authenticate(userDto.getUsername(), userDto.getPassword());
        if(user != null){
            model.addAttribute("username",user.getUsername());
            return "redirect:/chat";
        }
        model.addAttribute("error","Invalid username or password");
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam UserDto userDto){
        userService.saveUser(userDto.getUsername(), userDto.getPassword());
    }


}
