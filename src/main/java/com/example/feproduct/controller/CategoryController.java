package com.example.feproduct.controller;

import com.example.feproduct.model.Category;
import com.example.feproduct.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
@Controller
public class CategoryController extends BaseController {
    @Autowired
    private BaseService<Category> categoryService;

    @GetMapping("/categories")
    public String listCategories(Model model) {
        List<Category> listCategories = categoryService.getAllList();
        model.addAttribute("listCategories", listCategories);
        return "categories";
    }

    @GetMapping("/categories/new")
    public String showCategoryNewForm(Model model) {
        model.addAttribute("category", new Category());
        return "category_form";
    }

    @PostMapping("/categories/save")
    public String saveCategory(Category category, Model model) {
        categoryService.saveOrEdit(category);
        return "redirect:/categories";
    }

    @GetMapping("/categories/edit/{id}")
    public String showEditCategoryForm(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.getOneById(id);
        model.addAttribute("category", category);
        return "category_form";
    }

    @GetMapping("/categories/delete/{id}")
    public String DeleteCategory(@PathVariable("id") Integer id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }

}
