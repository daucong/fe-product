package com.example.feproduct.client;

import com.example.feproduct.entity.Product;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ProductClient {
    RestTemplate restTemplate = new RestTemplate();

    final String ROOT_URI = "http://localhost:8080/api/products";

    public List<Product> getAllProduct() {
        ResponseEntity<Product[]> response = restTemplate.getForEntity(ROOT_URI, Product[].class);
        return Arrays.asList(response.getBody());

    }


    public void saveProduct(Product product) {
        product.setCategory_id(null);
        System.out.println(new Gson().toJson(product));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(new Gson().toJson(product).toString(), headers);
        String response = restTemplate.postForObject(ROOT_URI + "/", request, String.class);
//        ResponseEntity<User> response = restTemplate.postForEntity(ROOT_URI, user, User.class);
//        return response.getBody();
    }


    public void deleteProduct(Integer id) {
        restTemplate.delete(ROOT_URI + "/" + id);
    }


    public Product findProductById(Integer id) {
        ResponseEntity<Product> response = restTemplate.getForEntity(ROOT_URI + "/" + id, Product.class);
        return response.getBody();
    }
}
