package com.example.shoppingcart.service;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CartService {

    private final ProductRepository productRepository;
    private final double TAX_RATE = 22.0;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getCartItems() {
        return productRepository.findAll();
    }

    public void removeProduct(String name) {
        productRepository.deleteByName(name);
    }

    public double calculateTax() {
        return 1 + TAX_RATE / 100;
    }

    public double calculateCartTotal() {
        List<Product> products = productRepository.findAll();
        double total = 0;

        for (Product product : products) {
            total += product.calculateTotal();
        }

        return total * calculateTax();
    }

    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> getProductsSortedByPrice() {
        return productRepository.findAll(Sort.by(Sort.Order.asc("price")));
    }
}
