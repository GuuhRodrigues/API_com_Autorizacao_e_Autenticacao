package com.example.Av2.service;

import com.example.Av2.model.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public class ProductService {

    private Map<String, Product> productRepository = new HashMap<>();

    // Método para encontrar um produto pelo ID
    public Product findProductById(String id) {
        return productRepository.get(id);
    }

    // Método para deletar um produto pelo ID
    public boolean deleteProductById(String id) {
        return productRepository.remove(id) != null;
    }

    // Método para criar um novo produto
    public Product createProduct(Product product) {
        productRepository.put(product.getId(), product);
        return product;
    }
}
