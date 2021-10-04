package com.dharani.homepage.service;


import com.dharani.homepage.model.LoginModel;
import com.dharani.homepage.model.User;

import java.util.List;

public interface HomeService {
    List<User> checkLogin(LoginModel loginModel);
    User addUser(User user);
}
