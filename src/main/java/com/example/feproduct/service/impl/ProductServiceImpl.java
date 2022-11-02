package com.example.feproduct.service.impl;


import com.example.feproduct.client.ProductClient;
import com.example.feproduct.entity.Product;
import com.example.feproduct.service.ProductService;
import com.example.feproduct.utils.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    ProductClient productClient = new ProductClient();
    @Autowired
    private ImageUpload imageUpload;

    @Override
    public List<Product> getAllProduct() {
        return productClient.getAllProduct();
    }

    @Override
    public void saveProduct(MultipartFile imageProduct, Product product) {
        if (product.getId() != null) {
            Product oldImg = new Product();
            oldImg = productClient.findProductById(product.getId());
            try {
                if (imageProduct.getOriginalFilename().length() == 0) {
                    product.setImage(oldImg.getImage());
                } else {
                    product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                imageUpload.uploadImage(imageProduct);
                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        productClient.saveProduct(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productClient.deleteProduct(id);
    }

    @Override
    public Product findProductById(Integer id) {
        return productClient.findProductById(id);
    }
}
