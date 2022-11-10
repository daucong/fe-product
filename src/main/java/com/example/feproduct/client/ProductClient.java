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
import java.util.Optional;

public class ProductClient {
    RestTemplate restTemplate = new RestTemplate();

    final String ROOT_URI = "http://localhost:8080/api/products";

    public void saveProduct(Product product) {
//        product.setCategory_id(null);
//        System.out.println(new Gson().toJson(product));
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> request = new HttpEntity<String>(new Gson().toJson(product).toString(), headers);
//        String response = restTemplate.postForObject(ROOT_URI + "/", request, String.class);
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

//    public List<Product> getAllProductPaging(Pageable pageable, String query) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Accept", "application/json");
//        HttpEntity entity = new HttpEntity(headers);
//        String url = ROOT_URI+"/search"+"?page="+pageable.getPage()+"&limit="+pageable.getLimit()
//                +"&sortname="+pageable.getSortname()+"&sortby="+pageable.getSortby()+"&query="+query;
//
//        HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//        System.out.println(response);
//
//        JsonObject responseJSON = JsonParser.parseString(response.getBody()).getAsJsonObject();
//        //get  totalPage and totel Item
//        pageable.setTotalPage(Integer.parseInt(responseJSON.get(("totalPages")).toString()));
//        pageable.setTotalItem(Integer.parseInt(responseJSON.get(("totalElements")).toString()));
//        //get list product
//        JsonArray json = JsonParser.parseString(responseJSON.get("content").toString()).getAsJsonArray();
//        List<Product> lists = new Gson().fromJson(json, new TypeToken<List<Product>>() {}.getType());
//        return lists;
//    }
    public List<Product> getAllWithPage(Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity entity = new HttpEntity(headers);
        String url = ROOT_URI+"?page="+pageable.getPage()+"&limit="+pageable.getLimit()
                +"&sortname="+pageable.getSortname()+"&sortby="+pageable.getSortby();

        HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(response);

        JsonObject responseJSON = JsonParser.parseString(response.getBody()).getAsJsonObject();
        //get  totalPage and totel Item
        pageable.setTotalPage(Integer.parseInt(responseJSON.get(("totalPages")).toString()));
        pageable.setTotalItem(Integer.parseInt(responseJSON.get(("totalElements")).toString()));
        //get list product
        JsonArray json = JsonParser.parseString(responseJSON.get("content").toString()).getAsJsonArray();
        List<Product> lists = new Gson().fromJson(json, new TypeToken<List<Product>>() {}.getType());
        return lists;
    }
    public List<Product> getAllWithPage(Pageable pageable, String query, StringBuilder message) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity entity = new HttpEntity(headers);
        String url = ROOT_URI+"?page="+pageable.getPage()
                +"&limit="+pageable.getLimit()
                +"&sortname="+pageable.getSortname()
                +"&sortby="+pageable.getSortby()
                +"&query="+query;
        List<Product> lists = new ArrayList<>();
        try {
            HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println(response);

            JsonObject responseJSON = JsonParser.parseString(response.getBody()).getAsJsonObject();
            //get  totalPage and totel Item
            pageable.setTotalPage(Integer.parseInt(responseJSON.get(("totalPages")).toString()));
            pageable.setTotalItem(Integer.parseInt(responseJSON.get(("totalElements")).toString()));
            //get list product
            JsonArray json = JsonParser.parseString(responseJSON.get("content").toString()).getAsJsonArray();
            lists = new Gson().fromJson(json, new TypeToken<List<Product>>() {}.getType());
        }catch (Exception e){
            System.out.println(e.getMessage());
            message.append(e.getMessage());
        }
        return lists;
    }
}
