package com.dharani.homepage.controller;

import com.dharani.homepage.DTO.AddressDTO;
import com.dharani.homepage.model.OrderModel;
import com.dharani.homepage.model.ProductModel;
import com.dharani.homepage.model.User;
import com.dharani.homepage.service.HomeServiceImpl;

import com.dharani.homepage.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShopController {

    @Autowired
    private HomeServiceImpl homeService;

    @Autowired
    private OrderServiceImpl orderService;

    //Product Detials
    @GetMapping("/shop")
    public String products(final Model model) {
        List<ProductModel> productList = homeService.getAllProduct();
        System.out.println(productList);
        model.addAttribute("list", productList);
        model.addAttribute("image","https://fdn2.gsmarena.com/vv/pics/oneplus/oneplus-nord-1.jpg");
        return "product.html";
    }

    @GetMapping("/product/view/{id}")
    public String GetProduct(final Model model, @PathVariable final int id) {
        final ProductModel productModel = homeService.getProduct(id);
        System.out.println("Product"+productModel.getProductId());
        model.addAttribute("lists", productModel);
        return "productView.html";
    }

    @GetMapping("/cart/add/{id}")
    public String addCart(@PathVariable final int id, HttpServletRequest request) {
        System.out.println("Cart Add");
        List<Integer> messages = (List<Integer>) request.getSession().getAttribute("Session_Id");
        for (int i = 0; i < messages.size(); i++) {
            System.out.println(messages.get(i) + ".......Cart......" + request.getSession().getId() + "  " + request.getRequestedSessionId());
        }
        final int userId = messages.get(0);
        homeService.addCart(id, userId);
        return "redirect:/shop";
    }

    @GetMapping("/cart/delete/{id}")
    public String deleteCart(@PathVariable final int id) {
        System.out.println(id);
        homeService.deleteCart(id);
        return "redirect:/checkout";
    }

    @GetMapping("/checkout")
    public String checkOut(final Model model, HttpServletRequest request) {
        List<Integer> messages = (List<Integer>) request.getSession().getAttribute("Session_Id");
        final int userId = messages.get(0);
        final List<ProductModel> cart = homeService.getCart(userId);
        model.addAttribute("list", cart);
        System.out.println(cart);
        return "checkOut.html";
    }

    @GetMapping("/confirm")
    public String checkoutSuccess(final Model model, HttpServletRequest request) {
        List<Integer> messages = (List<Integer>) request.getSession().getAttribute("Session_Id");
        final int userId = messages.get(0);
        final User user = homeService.getUser(userId);
        model.addAttribute("user", user);
        return "confirm.html";
    }

    @PostMapping("/address")
    public String updateAddress(@PathParam(value = "address") User address, HttpServletRequest request) {
        List<Integer> messages = (List<Integer>) request.getSession().getAttribute("Session_Id");
        final int userId = messages.get(0);
        int orderModel = orderService.addAddress(address, messages);
        messages.add(orderModel);
        request.getSession().setAttribute("Session_Id", messages);

        return "payment.html";
    }

}