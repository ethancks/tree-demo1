package com.lemon.tree.controller;

import com.lemon.tree.db.model.Size;
import com.lemon.tree.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handle all sales relevant APIs
 */
@RestController
public class SalesController {
    @Autowired
    private OrderingService orderingService;


    @PutMapping(value = "/stock/sales")
    public ResponseEntity<String> sales(@RequestParam() String country, @RequestParam() Size size, @RequestParam int salesQuantity) {
        return orderingService.newOrder(country, size, salesQuantity);
    }
}
