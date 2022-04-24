package com.lemon.tree.controller;

import com.lemon.tree.db.model.Lemon;
import com.lemon.tree.db.model.Orders;
import com.lemon.tree.db.model.Size;
import com.lemon.tree.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Handle all inventory checking relevant APIs
 */
@RestController
public class WarehouseController {
    @Autowired
    private OrderingService orderingService;

    @GetMapping("/query/sample")
    public Lemon querySample() {
        return new Lemon("MY", Size.MEDIUM, 10);
    }

    @GetMapping("/query")
    public List<Lemon> queryAllStocks() {
        return orderingService.queryAllStocks();
    }

    @GetMapping("/query/{country}")
    public List<Lemon> queryStockByCountry(@PathVariable("country") String country) {
        return orderingService.queryStock(country);
    }

    @GetMapping("/query/allOrdersInPage")
    public Page<Orders> queryAllOrderInPage(@RequestParam() int pageSize, @RequestParam() int pageNumber, @RequestParam(required = false) Sort.Direction sortDirection, @RequestParam(required = false) String sortBy) {
        return orderingService.getOrderPagination(pageSize, pageNumber, sortDirection, sortBy);
    }
}
