package com.example.Av2.service;

import com.example.Av2.model.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {

    private Map<String, Product> productRepository = new HashMap<>();

    public Product findProductById(String id) {
        return productRepository.get(id);
    }

    public boolean deleteProductById(String id) {
        return productRepository.remove(id) != null;
    }

    public Product createProduct(Product product) {
        productRepository.put(product.getId(), product);
        return product;
    }
}
