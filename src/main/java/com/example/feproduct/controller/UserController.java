package com.example.feproduct.controller;

import com.example.feproduct.model.User;
import com.example.feproduct.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserController extends BaseController {
    @Autowired
    private BaseService<User> userService;

    @GetMapping("")
    public String listUsers(Model model) {
        List<User> listUsers = userService.getAllList();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/new")
    public String showUserNewForm(Model model) {
        model.addAttribute("user", new User());
        return "user_form";
    }

    @PostMapping("/save")
    public String saveUser(User user, Model model) {
        userService.saveOrEdit(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.getOneById(id);
        model.addAttribute("user", user);
        return "user_form";
    }

    @GetMapping("/delete/{id}")
    public String DeleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

}
