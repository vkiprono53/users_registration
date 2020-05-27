package com.vkiprono.usersregistration.controllers;

import com.vkiprono.usersregistration.models.UserReg;
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
@RequestMapping("/")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        System.out.println("********Retrieving data from db*******");
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/users/register")
    public String showAddForm(UserReg userReg) {
        System.out.println("**********Showing form********");
        return "add_user";

    }

    @PostMapping("/users/add")
    public String createUser(@Valid UserReg userReg, BindingResult bindingResult, Model model) {
        System.out.println("**********Beginning to add user********");

        if (bindingResult.hasErrors()) {
            return "add_user";
        }
        userService.saveUser(userReg);
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/";
    }

    @GetMapping("users/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        UserReg userReg = userService.findUserById(id).
                orElseThrow(() -> new IllegalArgumentException("User with " + id + "Cannot be found"));

        userService.deleteUser(userReg);
        model.addAttribute("users", userService.getAllUsers());

//        return "index";
        return "redirect:/";
    }

    @GetMapping("users/editUser/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        UserReg userReg = userService.findUserById(id).
                orElseThrow(() -> new IllegalArgumentException("User with " + id + "Cannot be found"));
        model.addAttribute("userReg", userReg);
        return "update_user";
    }

    @PostMapping("users/updateUser/{id}")
    public String editUser(@PathVariable Long id, @Valid UserReg userReg, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            userReg.setId(id);
            return "update_user";
        }
        userService.saveUser(userReg);
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/";
    }
}
