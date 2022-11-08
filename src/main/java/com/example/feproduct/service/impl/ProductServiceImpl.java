package com.example.feproduct.service.impl;


import com.example.feproduct.client.ProductClient;
import com.example.feproduct.model.Pageable;
import com.example.feproduct.model.Product;
import com.example.feproduct.service.BaseService;
import com.example.feproduct.utils.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements BaseService<Product> {
    ProductClient productClient = new ProductClient();
    @Autowired
    private ImageUpload imageUpload;

    @Override
    public List<Product> getAllList() {
        return null;
    }

    @Override
    public void saveOrEdit(Product entity) {

    }

    @Override
    public void saveOrEditWithImg(MultipartFile imageProduct, Product product) {
        if (product.getId() != null) {
            Product oldImg = new Product();
            oldImg = productClient.findProductById(product.getId());
            try {
                if (imageProduct.getOriginalFilename().length() == 0) {
                    product.setImage(oldImg.getImage());
                } else {
                    imageUpload.uploadImage(imageProduct);
                    String fileName = StringUtils.cleanPath(imageProduct.getOriginalFilename());
                    product.setImage(fileName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            imageUpload.uploadImage(imageProduct);
            String fileName = StringUtils.cleanPath(imageProduct.getOriginalFilename());
            product.setImage(fileName);
        }
        productClient.saveProduct(product);
    }

    @Override
    public void delete(Integer id) {
        productClient.deleteProduct(id);
    }

    @Override
    public Product getOneById(Integer id) {
        return productClient.findProductById(id);
    }

    @Override
    public List<Product> getAllListPaging(Pageable pageable) {
        List<Product> products = productClient.getAllProductPaging(pageable);
        String base64 = "";
        for (Product i : products) {
            String urlPath = imageUpload.UPLOAD_FOLDER + "\\" + i.getImage();
            Path path = Paths.get(urlPath);
            try {
                byte[] arr = Files.readAllBytes(path);
                base64 = Base64.getEncoder().encodeToString(arr);
                i.setImage(base64);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return products;
    }

//    @Override
//    public List<Product> getAllProduct() {
//        List<Product> products = productClient.getAllProduct();
//        String base64 = "";
//        for (Product i : products) {
//            String urlPath = imageUpload.UPLOAD_FOLDER + "\\" + i.getImage();
//            Path path = Paths.get(urlPath);
////            try {
////                byte[] arr = Files.readAllBytes(path);
////                base64 = Base64.getEncoder().encodeToString(arr);
////                i.setImage(base64);
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//            i.setImage(String.valueOf(path));
//        }
//        return products;
//    }
//
//    @Override
//    public void saveProduct(MultipartFile imageProduct, Product product) {
//        if (product.getId() != null) {
//            Product oldImg = new Product();
//            oldImg = productClient.findProductById(product.getId());
//            try {
//                if (imageProduct.getOriginalFilename().length() == 0) {
//                    product.setImage(oldImg.getImage());
//                } else {
//                    imageUpload.uploadImage(imageProduct);
//                    String fileName = StringUtils.cleanPath(imageProduct.getOriginalFilename());
//                    product.setImage(fileName);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            imageUpload.uploadImage(imageProduct);
//            String fileName = StringUtils.cleanPath(imageProduct.getOriginalFilename());
//            product.setImage(fileName);
//        }
//        productClient.saveProduct(product);
//    }
//
//    @Override
//    public void deleteProduct(Integer id) {
//        productClient.deleteProduct(id);
//    }
//
//    @Override
//    public Product findProductById(Integer id) {
//        return productClient.findProductById(id);
//    }
//
//    @Override
//    public int totalItem() {
//        List<Product> total = productClient.getAllProduct();
//        return (int) total.size();
//    }
//
//    @Override
//    public List<Product> getAllProductPaging(Pageable pageable){
//        List<Product> products = productClient.getAllProductPaging(pageable);
//        String base64 = "";
//        for (Product i : products) {
//            String urlPath = imageUpload.UPLOAD_FOLDER + "\\" + i.getImage();
//            Path path = Paths.get(urlPath);
//            try {
//                byte[] arr = Files.readAllBytes(path);
//                base64 = Base64.getEncoder().encodeToString(arr);
//                i.setImage(base64);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return products;
//    }
}
