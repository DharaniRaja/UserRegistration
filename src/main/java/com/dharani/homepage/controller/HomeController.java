package com.dharani.homepage.controller;

import com.dharani.homepage.model.DateModel;
import com.dharani.homepage.model.LoginModel;
import com.dharani.homepage.model.ProductModel;
import com.dharani.homepage.model.User;
import com.dharani.homepage.exception.InvalidNameException;
import com.dharani.homepage.exception.InvalidPasswordException;
import com.dharani.homepage.exception.InvalidPhNoException;
import com.dharani.homepage.service.HomeServiceImpl;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class HomeController {
    private final Log log = LogFactory.getLog(HomeController.class);

    @Autowired
    private HomeServiceImpl homeService;


    @GetMapping("/home")
    public String homePage() {

        return "Home.html";
    }

    @GetMapping("/login")
    public String loginPage(HttpSession session) {
/*        List<Integer> messages = (List<Integer>) session.getAttribute("MY_SESSION_MESSAGES");
        if (messages == null) {
            messages = new ArrayList<>();
        }*/
        return "login.html";
    }

    @GetMapping("/aboutUs")
    public String aboutUs() {
        return "aboutUs.html";
    }

    @PostMapping("/login")
    public String getLoginData(final LoginModel loginModel, final Model model, HttpServletRequest request) throws JRException, FileNotFoundException {
        log.info("Login Controller");
        List<Integer> messages = (List<Integer>) request.getSession().getAttribute("Session_Id");
        if (messages == null) {
            messages = new ArrayList<>();
            request.getSession().setAttribute("Session_Id", messages);
        }
        final User users = homeService.checkLogin(loginModel);
        if (users != null) {
            List<DateModel> loginDate = homeService.getLoginDate(users.getUserId());
            if (!messages.contains(users.getUserId())) {
                System.out.println("HI");
                messages.add(users.getUserId());
            }

            request.getSession().setAttribute("Session_Id", messages);
//            model.addAttribute("Date", loginDate);
            model.addAttribute("list", users);
//            model.addAttribute("userId", users.getUserId());

            System.out.println("After");
            for (int i = 0; i < messages.size(); i++) {
                System.out.println(messages.get(i) + "............." + request.getSession().getId() + "  " + request.getRequestedSessionId());
            }
            System.out.println(users.getName());

            return "display.html";
        }
        model.addAttribute("errorMsg", "Enter Valid UserName or Password");

        return "display.html";
    }

    @GetMapping("/newUser")
    public String userPage() {

        return "newUser.html";
    }

    @PostMapping("/newUser")
    public String createUser(final User user, final Model model) throws InvalidNameException, InvalidPhNoException, InvalidPasswordException {
        log.info("Create User Controller");
        final HashMap<String, String> hashMap = homeService.addUser(user);
        System.out.println(user.getName());
        final boolean success = hashMap.containsKey("success");

        if (success) {
            return "Home.html";
        } else {
            log.info("Login");
            for (Map.Entry m : hashMap.entrySet()) {
                model.addAttribute((String) m.getKey(), m.getValue());
            }
            return "newUser.html";
        }
    }

    /*//Product Detials
    @GetMapping("/shop")
    public String products(final Model model) {
        List<ProductModel> productList = homeService.getAllProduct();
        System.out.println(productList);
        model.addAttribute("list", productList);
        return "product.html";
    }

    @GetMapping("/product/view/{id}")
    public String GetProduct(final Model model, @PathVariable final int id) {
        final ProductModel productModel = homeService.getProduct(id);
        model.addAttribute("lists", productModel);
        return "productView.html";
    }

    @GetMapping("/cart/add/{id}")
    public String addCart(@PathVariable final int id,HttpServletRequest request){
        List<Integer> messages = (List<Integer>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
        for (int i = 0; i < messages.size(); i++) {
            System.out.println(messages.get(i) + ".......Cart......" + request.getSession().getId() + "  " + request.getRequestedSessionId());
        }
        int userId = messages.get(0);
        homeService.addCart(id,userId);
        return "redirect:/shop";
    }*/

    //Logout
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "Home.html";
    }

}