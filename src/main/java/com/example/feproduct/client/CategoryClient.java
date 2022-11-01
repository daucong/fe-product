package com.example.feproduct.client;

import com.example.feproduct.entity.Category;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class CategoryClient {
    RestTemplate restTemplate = new RestTemplate();

    final String ROOT_URI = "http://localhost:8080/api/categories";

    public List<Category> getAllCategory() {
        ResponseEntity<Category[]> response = restTemplate.getForEntity(ROOT_URI, Category[].class);
        return Arrays.asList(response.getBody());

    }


    public void saveCategory(Category category) {
        System.out.println(new Gson().toJson(category));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(new Gson().toJson(category).toString(), headers);
        String response = restTemplate.postForObject(ROOT_URI+ "/", request, String.class);
//        ResponseEntity<User> response = restTemplate.postForEntity(ROOT_URI, user, User.class);
//        return response.getBody();
    }

    public Category findCategoryById(Integer id) {
        ResponseEntity<Category> response = restTemplate.getForEntity(ROOT_URI + "/" + id, Category.class);
        return response.getBody();
    }
    public Category findCategoryByNam(String name) {
        ResponseEntity<Category> response = restTemplate.getForEntity(ROOT_URI + "/name/" + name, Category.class);
        return response.getBody();
    }

    public void deleteCategory(Integer id) {
        restTemplate.delete(ROOT_URI + "/" + id);
    }

}
