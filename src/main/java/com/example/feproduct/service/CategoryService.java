package com.example.feproduct.service;


import com.example.feproduct.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();

    void saveCategory(Category category);

    Category findCategoryById(Integer id);

    Category getCategoryByName(String name);

    void deleteCategory(Integer id);
}
