package com.example.feproduct.service.impl;

import com.example.feproduct.client.CategoryClient;
import com.example.feproduct.model.Category;
import com.example.feproduct.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    CategoryClient categoryClient = new CategoryClient();

    @Override
    public List<Category> getAllCategory() {
        return categoryClient.getAllCategory();
    }

    @Override
    public void saveCategory(Category category) {
        categoryClient.saveCategory(category);
    }

    @Override
    public Category findCategoryById(Integer id) {
        return categoryClient.findCategoryById(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryClient.findCategoryByNam(name);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryClient.deleteCategory(id);
    }
}