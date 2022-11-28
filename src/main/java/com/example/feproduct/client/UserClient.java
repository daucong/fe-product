package com.example.feproduct.client;

import com.example.feproduct.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class UserClient {
    RestTemplate restTemplate = new RestTemplate();

    final String ROOT_URI = "http://localhost:8080/api/users";

    public List<User> getAllUser() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(ROOT_URI, User[].class);
        return Arrays.asList(response.getBody());

    }

    public void addUser(User user) {
        ResponseEntity<User> response = restTemplate.postForEntity(ROOT_URI, user, User.class);
        response.getBody();
    }

    public User findUserById(Integer id) {
        ResponseEntity<User> response = restTemplate.getForEntity(ROOT_URI + "/" + id, User.class);
        return response.getBody();
    }

    public void DeleteUser(Integer id) {
        restTemplate.delete(ROOT_URI + "/" + id);
    }

}
