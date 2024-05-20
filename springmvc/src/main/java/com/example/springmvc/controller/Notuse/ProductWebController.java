package com.example.springmvc.controller.Notuse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductWebController {

    private List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1,"Apple", 1.20),
            new Product(2,"Banana", 0.75),
            new Product(3, "Cherry", 2.05)
    ));

    @GetMapping("/products")
    public String productsController(Model model){
        model.addAttribute("products", products);
        return "products";
    }


    @AllArgsConstructor
    @Getter
    @Setter
    static class Product{
        private Integer id;
        private String productName;
        private double price;
    }
}
