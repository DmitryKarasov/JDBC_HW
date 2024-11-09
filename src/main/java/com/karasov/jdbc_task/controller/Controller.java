package com.karasov.jdbc_task.controller;

import com.karasov.jdbc_task.repository.MyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final MyRepository repository;

    @GetMapping("/products/fetch-product")
    public ResponseEntity<String> getProductName(@RequestParam String name) {
        String productName = repository.getProductNameByCustomerName(name);

        return productName == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(productName);
    }
}
