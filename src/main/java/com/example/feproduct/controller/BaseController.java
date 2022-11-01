package com.example.feproduct.controller;

import org.springframework.ui.Model;

public class BaseController {
    public void ShowMessage(Model model, String message, String type) {
        model.addAttribute("AlertMessage", message);
        if (type == "success") {
            model.addAttribute("AlertType", "alert-success");
        } else if (type == "warning") {
            model.addAttribute("AlertType", "alert-warning");
        } else if (type == "error") {
            model.addAttribute("AlertType", "alert-danger");
        }
    }
}
