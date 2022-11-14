package com.example.feproduct.service.impl;

import com.example.feproduct.client.CategoryClient;
import com.example.feproduct.client.UserClient;
import com.example.feproduct.model.Category;
import com.example.feproduct.model.Pageable;
import com.example.feproduct.model.User;
import com.example.feproduct.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserServiceImpl implements BaseService<User> {
    UserClient userClient = new UserClient();

    @Override
    public List<User> getAllList() {
        return userClient.getAllUser();
    }

    @Override
    public void saveOrEditWithImg(MultipartFile imageProduct, User entity) {

    }

    @Override
    public void saveOrEdit(User entity) {
        userClient.addUser(entity);
    }

    @Override
    public void delete(Integer id) {
        userClient.DeleteUser(id);
    }

    @Override
    public User getOneById(Integer id) {
        return userClient.findUserById(id);
    }

    @Override
    public List<User> getAllListPagingAndSearch(Pageable pageable, String query, StringBuilder message) {
        return null;
    }

    @Override
    public List<User> getAllWithPage(Pageable pageable) {
        return null;
    }
}