package com.example.shoppingcart.controller;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody Product product) {
        service.addProduct(product);
        return "Product " + product.getName() + " added successfully";
    }

    @GetMapping("/items")
    public List<Product> getCartItems() {
        return service.getCartItems();
    }

    @DeleteMapping("/remove/{name}")
    public void removeProduct(@PathVariable String name) {
        service.removeProduct(name);
    }

    @GetMapping("/total")
    public double calculateCartTotal() {
        return service.calculateCartTotal();
    }
}
