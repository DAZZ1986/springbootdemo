package com.prosalite.springbootdemo.controller;

import com.prosalite.springbootdemo.model.User;
import com.prosalite.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.AttributedString;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }




    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();       //тут GET и поэтому тянем из БД передали на фронт через атрибут
        model.addAttribute("usersAtt", users);
        return "user-list";
    }


    @GetMapping("/user-create")
    public String createUserFrom(User userObjParamCreate, Model model) {
        User aaa = new User();
        aaa.setFirstNameAAA("aaaaaaaaaaaaaaa");
        model.addAttribute("userObjParamCreate", aaa);
        return "/user-create";
    }
    @PostMapping("/user-create")                        //тут POST и поэтому с фронта добавляем в БД
    public String createUser(User userObjParamCreate) {
        userService.saveUser(userObjParamCreate);
        return "redirect:/users";
    }



    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }



    @GetMapping("/user-update/{id}")
    public String updateUserFrom(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("userObjParam", user);
        return "/user-update";
    }
    @PostMapping("/user-update")
    public String updateUser(User userObjParam) {
        userService.saveUser(userObjParam);
        return "redirect:/users";
    }

}
