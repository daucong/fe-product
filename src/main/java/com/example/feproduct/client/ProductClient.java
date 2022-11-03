package com.example.feproduct.client;

import com.example.feproduct.model.Pageable;
import com.example.feproduct.model.Product;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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

    public List<Product> getAllProductPaging(Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity entity = new HttpEntity(headers);
        String url = ROOT_URI+"/paging"+"?page="+pageable.getPage()+"&limit="+pageable.getLimit()
                +"&sortname="+pageable.getSortname()+"&sortby="+pageable.getSortby();

        HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(response);

        JsonObject outputJsonArray = JsonParser.parseString(response.getBody()).getAsJsonObject();

        System.out.println( outputJsonArray.get("page") );
        System.out.println( outputJsonArray.get("totalPage") );
        JsonArray json = JsonParser.parseString(outputJsonArray.get("listResult").toString()).getAsJsonArray();

        List<Product> listsss = new Gson().fromJson(json, new TypeToken<List<Product>>() {}.getType());
        pageable.setTotalPage(Integer.parseInt(outputJsonArray.get("totalPage").toString()));
        return listsss;
    }
}
