package com.dharani.homepage.service;

import com.dharani.homepage.model.DateModel;
import com.dharani.homepage.model.LoginModel;
import com.dharani.homepage.model.ProductModel;
import com.dharani.homepage.model.User;
import com.dharani.homepage.exception.InvalidNameException;
import com.dharani.homepage.exception.InvalidPasswordException;
import com.dharani.homepage.exception.InvalidPhNoException;
import net.sf.jasperreports.engine.JRException;
import org.springframework.ui.Model;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;


public interface HomeService {
    User checkLogin(LoginModel loginModel) throws FileNotFoundException, JRException;
    List<DateModel> getLoginDate(int UserId);
    HashMap<String, String> addUser(User user) throws InvalidNameException, InvalidPasswordException, InvalidPhNoException;

    List<ProductModel> getAllProduct();

    ProductModel getProduct(int id);

    void addCart(int id,int userId);
    List<ProductModel> getCart(int userId);

    User getUser(int id);

    void deleteCart(int id);
}