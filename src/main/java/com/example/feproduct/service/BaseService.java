package com.example.feproduct.service;

import com.example.feproduct.model.Pageable;
import com.example.feproduct.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    List<T> getAllList();

    void saveOrEditWithImg(MultipartFile imageProduct, T entity);

    void saveOrEdit(T entity);

    void delete(Integer id);

    T getOneById(Integer id);

    List<T> getAllListPaging(Pageable pageable);
}
