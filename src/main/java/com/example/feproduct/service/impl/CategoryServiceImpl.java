package com.example.feproduct.service.impl;

import com.example.feproduct.client.CategoryClient;
import com.example.feproduct.model.Category;
import com.example.feproduct.model.Pageable;
import com.example.feproduct.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements BaseService<Category> {
    CategoryClient categoryClient = new CategoryClient();

    @Override
    public List<Category> getAllList() {
        return categoryClient.getAllCategory();
    }

    @Override
    public void saveOrEditWithImg(MultipartFile imageProduct, Category entity) {

    }

    @Override
    public void saveOrEdit(Category entity) {
        categoryClient.saveCategory(entity);
    }

    @Override
    public void delete(Integer id) {
        categoryClient.deleteCategory(id);
    }

    @Override
    public Category getOneById(Integer id) {
        return categoryClient.findCategoryById(id);
    }

    @Override
    public List<Category> getAllListPagingAndSearch(Pageable pageable, String query, StringBuilder message) {
        return null;
    }

    @Override
    public List<Category> getAllWithPage(Pageable pageable) {
        return null;
    }

}