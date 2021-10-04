package com.dharani.homepage.service;

import com.dharani.homepage.model.LoginModel;
import com.dharani.homepage.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Arrays;
import java.util.List;


@Service
public class HomeServiceImpl implements HomeService{

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders httpHeaders ;

    @Override
    public List<User> checkLogin(LoginModel loginModel) {
        String url = "http://localhost:8088/login/get";
        httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<LoginModel> loginModelHttpEntity = new HttpEntity<>(loginModel,httpHeaders);
        ResponseEntity<List<User>> exchange = restTemplate.exchange(url, HttpMethod.POST, loginModelHttpEntity, new ParameterizedTypeReference<List<User>>() {});
        return exchange.getBody();
    }

    @Override
    public User addUser(User user) {
        String url = "http://localhost:8086/user/add";
        httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> userHttpEntity = new HttpEntity<>(user,httpHeaders);
        ResponseEntity<User> exchange = restTemplate.exchange(url, HttpMethod.POST, userHttpEntity, User.class);

        return exchange.getBody();
    }
}