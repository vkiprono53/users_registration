package com.vkiprono.usersregistration.controllers;

import com.vkiprono.usersregistration.models.User;
import com.vkiprono.usersregistration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Vkiprono on 5/27/20 ---2:08 PM
 * @project usersregistration
 */
@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("list")
    public String listUsers(Model model) {
        System.out.println("********Retrieving data from db*******");
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("register")
    public String showAddForm(User user) {
        System.out.println("**********Showing form********");
        return "add_user";

    }

    @PostMapping("add")
    public String createUser(@Valid User user, BindingResult bindingResult, Model model) {
        System.out.println("**********Beginning to add user********");

        if (bindingResult.hasErrors()) {
            return "add_user";
        }
        userService.saveUser(user);
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id).
                orElseThrow(() -> new IllegalArgumentException("User with " + id + "Cannot be found"));

        userService.deleteUser(user);
        model.addAttribute("users", userService.getAllUsers());

        return "index";
    }

    @GetMapping("editUser/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id).
                orElseThrow(() -> new IllegalArgumentException("User with " + id + "Cannot be found"));
        model.addAttribute("user", user);
        return "update_user";
    }

    @PostMapping("/updateUser/{id}")
    public String editUser(@PathVariable Long id, @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            user.setId(id);
            return "update_user";
        }
        userService.saveUser(user);
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }
}
