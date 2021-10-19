package com.dharani.homepage.service;

import com.dharani.homepage.configuration.AES;
import com.dharani.homepage.model.Cart;
import com.dharani.homepage.model.DateModel;
import com.dharani.homepage.model.LoginModel;
import com.dharani.homepage.model.ProductModel;
import com.dharani.homepage.model.User;
import com.dharani.homepage.exception.InvalidNameException;
import com.dharani.homepage.exception.InvalidPasswordException;
import com.dharani.homepage.exception.InvalidPhNoException;
import com.dharani.homepage.repository.CartRepository;
import com.dharani.homepage.repository.ProductRepository;
import com.dharani.homepage.validate.Validate;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private final Validate validate;

    @Autowired
    private final Environment environment;
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final CartRepository cartRepository;

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders httpHeaders;
    private final String secretKey = "UntrustedNetwork";


    public HomeServiceImpl(Validate validate,  Environment environment, ProductRepository productRepository, CartRepository cartRepository) {
        this.validate = validate;

        this.environment = environment;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }


    @Override
    public User checkLogin(final LoginModel loginModel) throws FileNotFoundException, JRException {
        final String url = "http://localhost:8088/login/get";
        httpHeaders = new HttpHeaders();
        final Base64 base64 = new Base64();
        final String encodedString = new String(base64.encode(loginModel.getPassword().getBytes()));
        final String encrypt = AES.encrypt(encodedString, secretKey);
        loginModel.setPassword(encrypt);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final HttpEntity<LoginModel> loginModelHttpEntity = new HttpEntity<>(loginModel, httpHeaders);
        final ResponseEntity<User> exchange = restTemplate.exchange(url, HttpMethod.POST, loginModelHttpEntity, User.class);
        final User ruser = exchange.getBody();


        return ruser;
    }

    @Override
    public List<DateModel> getLoginDate(final int userId) {
      //  final String url = environment.getProperty("com.dharn.homepageservices.getDate");
      //  final Map<String,Integer> map = new HashMap<>();
        //map.put("userId",userId);
     //   final DateModel[] forObject = restTemplate.getForObject(url, DateModel[].class, map);
       // final List<DateModel> modelList = new ArrayList<>();
      //  for (DateModel d :forObject){
        //    modelList.add(d);
  //      }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, SQLException.class})
    public HashMap<String, String> addUser(final User user) throws InvalidNameException, InvalidPasswordException, InvalidPhNoException, RuntimeException {
        final HashMap<String, String> hashMap = new HashMap<>();
       /* if ((!validate.validateEmail(user.getEmail().toUpperCase())) || (!validate.validateName(user.getName().toUpperCase())) || (!validate.isValidPassword(user.getPassword()))
                || (!validate.validatePhoneNumber(user))) {
            if (!validate.validateEmail(user.getEmail())) {
                hashMap.put("emailError", "Enter Valid Email");

            }
            if (!validate.validateName(user.getName())) {
                hashMap.put("nameError", "Enter Valid Name");
//                throw new InvalidNameException("Invalid Name");

            }
            if (!validate.isValidPassword(user.getPassword())) {
                hashMap.put("passwordError", "Enter Valid Password");
//                throw new InvalidPasswordException("Invalid Password");
            }
            if (!validate.validatePhoneNumber(user)) {
                hashMap.put("phoneError", "Enter Valid Phone Number");
            }
            return hashMap;
        }*/

        final String url = "http://localhost:8086/user/add";
        httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final Base64 base64 = new Base64();
        final String encodedString = new String(base64.encode(user.getPassword().getBytes()));
        final String encrypt = AES.encrypt(encodedString, secretKey);
        user.setPassword(encrypt);
        final HttpEntity<User> userHttpEntity = new HttpEntity<>(user, httpHeaders);
        final ResponseEntity<User> exchange = restTemplate.exchange(url, HttpMethod.POST, userHttpEntity, User.class);
        if (exchange != null) {
            hashMap.put("success", "Registeration Succesfull");
        } else {
            throw new NullPointerException();
        }
        return hashMap;
    }

    @Override
    public List<ProductModel> getAllProduct() {
        return (List<ProductModel>) productRepository.findAll();
    }

    @Override
    public ProductModel getProduct(int id) {
        return productRepository.findProductModelByProductId(id);
    }

    @Override
    public void addCart(int id,int userId) {
        final Cart cart = new Cart();
        cart.setProductId(id);
        cart.setUserId(userId);
        cartRepository.save(cart);
    }
    @Override
    public List<ProductModel> getCart(int userId) {
        List<Cart> list = (List<Cart>) cartRepository.findAll();
        List<ProductModel> productModels = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserId() == userId) {
                productModels.add(productRepository.findProductModelByProductId(list.get(i).getProductId()));
            }
        }
        return productModels;
    }

    @Override
    public User getUser(int id) {
        String url = environment.getProperty("com.dharn.loginService.login");
        User forObject = restTemplate.getForObject(url, User.class, id);
        return forObject;
    }

    @Override
    public void deleteCart(int id) {
        Cart orderId = cartRepository.findCartByProductId(id);
        cartRepository.deleteById(orderId.getCartId());
    }

}
