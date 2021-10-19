package com.dharani.homepage.controller;

import com.dharani.homepage.model.OrderModel;
import com.dharani.homepage.model.ProductModel;
import com.dharani.homepage.repository.ProductRepository;
import com.dharani.homepage.service.HomeServiceImpl;
import com.dharani.homepage.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PaymentController {


    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private HomeServiceImpl homeService;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/payment")
    public String paymentMethod(final String match, HttpServletRequest request) {
        List<String> messages1 = (List<String>) request.getSession().getAttribute("Home_Service");
        if (messages1 == null) {
            messages1 = new ArrayList<>();
            request.getSession().setAttribute("Home_Service", messages1);
        }
        messages1.add(match);
        if (match.equals("COD")) {

            request.getSession().setAttribute("Home_Service", messages1);
            return "redirect:/payment-success";
        } else if (match.equals("CreditOrDebit")) {

            request.getSession().setAttribute("Home_Service", messages1);
            return "redirect:/payment-success";
        }

        request.getSession().setAttribute("Home_Service", messages1);

        return "redirect:/payment-success";
    }

    @PostMapping("/netBanking")
    public String netBanking() {
        return "redirect:/payment-success";
    }

    @GetMapping("/payment-success")
    public String paymentSuccess(final Model model, HttpServletRequest request) {
        System.out.println("Hi payment");
        List<Integer> userId = (List<Integer>) request.getSession().getAttribute("Session_Id");
        List<String> paymentType = (List<String>) request.getSession().getAttribute("Home_Service");
//        OrderModel orderModel = orderService.addOrder(userId, paymentType);
        /*  model.addAttribute("order",orderModel);*/
        List<ProductModel> cart = homeService.getCart(userId.get(0));
        model.addAttribute("cart", cart);
//        System.out.println(orderModel.getCountry());
        return "payment-success.html";
    }

}