package com.hillel.crud.controller;

import com.hillel.crud.model.User;
import com.hillel.crud.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class UserController {

    @GetMapping("/add-user")
    public String addUserForm(User user) {
        return "add-user";
    }
    @GetMapping("/edit-user/{id}")
    public String editUserForm(User user){
        return "edit-user";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id,@Valid User user,Model model){

        user.setName(user.getName());
        user.setEmail(user.getEmail());
        userRepository.save(user);

        return "redirect:/";
    }
    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userRepository.save(user);
        return "redirect:/";
    }
@GetMapping("/delete/{id}")
public String deleteUser(@PathVariable("id") int id, Model model) {

    userRepository.deleteById(id);

    return "redirect:/";
}
    @GetMapping
    public String showUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }
    UserRepository userRepository;
    public UserController(UserRepository userRepository){
        this.userRepository=userRepository;
    }
}
