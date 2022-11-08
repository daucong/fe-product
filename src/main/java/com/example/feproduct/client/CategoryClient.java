package com.example.feproduct.client;

import com.example.feproduct.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CategoryClient {
    RestTemplate restTemplate = new RestTemplate();

    final String ROOT_URI = "http://localhost:8080/api/categories";

    public List<Category> getAllCategory() {
        ResponseEntity<Category[]> response = restTemplate.getForEntity(ROOT_URI, Category[].class);
        return Arrays.asList(response.getBody());

    }


    public void saveCategory(Category category) {
//        System.out.println(new Gson().toJson(category));
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> request = new HttpEntity<String>(new Gson().toJson(category).toString(), headers);
//        String response = restTemplate.postForObject(ROOT_URI+ "/", request, String.class);
        ResponseEntity<Category> response = restTemplate.postForEntity(ROOT_URI, category, Category.class);
        response.getBody();
    }

    public Category findCategoryById(Integer id) {
        ResponseEntity<Category> response = restTemplate.getForEntity(ROOT_URI + "/" + id, Category.class);
        return response.getBody();
    }

    public void deleteCategory(Integer id) {
        restTemplate.delete(ROOT_URI + "/" + id);
    }

}
