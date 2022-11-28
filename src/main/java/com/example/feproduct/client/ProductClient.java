package com.example.feproduct.client;

import com.example.feproduct.model.Pageable;
import com.example.feproduct.model.Product;
import com.example.feproduct.model.RestPageResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class ProductClient {
    RestTemplate restTemplate = new RestTemplate();

    final String ROOT_URI = "http://localhost:8080/api/products";

    public void saveProduct(Product product) {
        ResponseEntity<Product> response = restTemplate.postForEntity(ROOT_URI, product, Product.class);
        response.getBody();
    }


    public void deleteProduct(Integer id) {
        restTemplate.delete(ROOT_URI + "/" + id);
    }


    public Product findProductById(Integer id) {
        ResponseEntity<Product> response = restTemplate.getForEntity(ROOT_URI + "/" + id, Product.class);
        return response.getBody();
    }

    public List<Product> getAllWithPage(Pageable pageable, String query, StringBuilder message) {
        String url = ROOT_URI + "?page=" + pageable.getPage()
                + "&limit=" + pageable.getLimit()
                + "&sortname=" + pageable.getSortname()
                + "&sortby=" + pageable.getSortby()
                + "&query=" + query;
        List<Product> lists = new ArrayList<>();
        try {
            HttpEntity<RestPageResponse<Product>> response =
                    restTemplate.exchange(url, HttpMethod.GET, null,
                            new ParameterizedTypeReference<RestPageResponse<Product>>() {
                            });
            //get  totalPage and totel Item
            pageable.setTotalPage(response.getBody().getTotalPages());
            pageable.setTotalItem(Long.parseLong(String.valueOf(response.getBody().getTotalElements())));
            //get list product
            lists = response.getBody().getContent();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            message.append(e.getMessage());
        }
        return lists;
    }
}
