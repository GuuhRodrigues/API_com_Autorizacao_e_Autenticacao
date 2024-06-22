package com.example.Av2.controller;

import com.example.Av2.model.Product;
import com.example.Av2.model.User;
import com.example.Av2.service.AuthService;
import com.example.Av2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.Av2.service.ProductService;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String response = userService.registerUser(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String token = authService.authenticateUser(user.getUsername(), user.getPassword());
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @Secured("ADMIN")
    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        User user = userService.findUserById(id);
        if (user != null) {
            boolean isDeleted = userService.deleteUserById(id);
            if (isDeleted) {
                return ResponseEntity.ok("Usuário " + user.getUsername() + " deletado com sucesso");
            }
        }
        return ResponseEntity.status(404).body("Usuário não foi encontrado");
    }

    @Secured("MANAGER")
    @DeleteMapping("/manager/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        Product product = productService.findProductById(id);
        if (product != null) {
            boolean isDeleted = productService.deleteProductById(id);
            if (isDeleted) {
                return ResponseEntity.ok("Produto deletado com sucesso");
            }
        }
        return ResponseEntity.status(404).body("Produto não foi encontrado");
    }

    @Secured("SELLER")
    @PostMapping("/seller/orders")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok("Produto criado com sucesso");
    }

    @Secured("COSTUMER")
    @DeleteMapping("/customer/products/{id}")
    public ResponseEntity<Product> visualizeProduct(@PathVariable String id) {
        Product product = productService.findProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/role/{token}")
    public ResponseEntity<String> extractRole(@PathVariable String token) {
        String role = authService.extractRole(token);
        if (role != null) {
            System.out.println("Role extraída: " + role);
            return ResponseEntity.ok(role);
        } else {
            System.err.println("Role não encontrada para o token: " + token);
            return ResponseEntity.status(400).body("Role not found");
        }
    }
}
