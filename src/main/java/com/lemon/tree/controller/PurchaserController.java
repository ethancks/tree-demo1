package com.lemon.tree.controller;

import com.lemon.tree.db.model.Lemon;
import com.lemon.tree.db.model.Size;
import com.lemon.tree.service.PurchasingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Handle all stock producing relevant APIs
 */
@RestController
public class PurchaserController {
    @Autowired
    private PurchasingService purchasingService;

    @PostMapping(value = "/stock/new")
    public Lemon newStock(@RequestBody() Lemon newStock) {
        return purchasingService.addStock(newStock.getCountry(), newStock.getSize(), newStock.getQuantity());
    }

    @PutMapping(value = "/stock/add")
    public Lemon addStock(@RequestParam() String country, @RequestParam() Size size, @RequestParam int addInQuantity) {
        return purchasingService.addStock(country, size, addInQuantity);
    }
}
