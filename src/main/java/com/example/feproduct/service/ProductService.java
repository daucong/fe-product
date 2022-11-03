package com.example.feproduct.service;

import com.example.feproduct.model.Pageable;
import com.example.feproduct.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    void saveProduct(MultipartFile imageProduct, Product product);

    void deleteProduct(Integer id);

    Product findProductById(Integer id);

    List<Product> getAllProductPaging(Pageable pageable);

    int totalItem();
}
