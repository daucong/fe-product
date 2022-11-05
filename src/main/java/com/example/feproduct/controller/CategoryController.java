package com.example.feproduct.controller;

import com.example.feproduct.model.Category;
import com.example.feproduct.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController extends BaseController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String listCategories(Model model) {
        List<Category> listCategories = categoryService.getAllCategory();
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
//        String oldCategory= category.getName();
//        Category newName = categoryService.getCategoryByName(oldCategory);
//        if(newName!=null) {
//            ShowMessage(model, "Tên danh mục đã tồn tại", "error");
//            if (category.getId() != null)
//                return showEditCategoryForm(category.getId(), model);
//            else
//                return showCategoryNewForm(model);
//        }
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/categories/edit/{id}")
    public String showEditCategoryForm(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.findCategoryById(id);
        model.addAttribute("category", category);
        return "category_form";
    }

    @GetMapping("/categories/delete/{id}")
    public String DeleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

}
