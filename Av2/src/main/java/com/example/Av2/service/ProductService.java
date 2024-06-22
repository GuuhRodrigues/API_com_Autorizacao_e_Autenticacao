package com.example.Av2.service;

import com.example.Av2.model.Product;
import com.example.Av2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public boolean deleteProductById(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
