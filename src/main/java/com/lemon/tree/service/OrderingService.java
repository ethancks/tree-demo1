package com.lemon.tree.service;

import com.lemon.tree.db.model.Lemon;
import com.lemon.tree.db.model.Orders;
import com.lemon.tree.db.model.Size;
import com.lemon.tree.db.repository.LemonRepo;
import com.lemon.tree.db.repository.OrderingRepository;
import com.lemon.tree.util.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderingService {
    @Autowired
    private LemonRepo lemonRepo;
    @Autowired
    private OrderingRepository orderingRepository;

    public List<Lemon> queryAllStocks() {
        return lemonRepo.findAll();
    }

    public List<Lemon> queryStock(String country) {
        return lemonRepo.findBy(country);
    }

    public Page<Orders> getOrderPagination(int pageSize, int pageNumber, Sort.Direction sortDirection, String sortBy) {
        // Simulate data if it is empty
        if (orderingRepository.count() <= 40) {
            populateOrders();
        }

        var direction0 = sortDirection == null ? Sort.Direction.ASC : sortDirection;
        var sortBy0 = sortBy == null ? "orderDate" : sortBy;

        Pageable paging = PageRequest.of(pageNumber, pageSize, direction0, sortBy0);
        return orderingRepository.findAll(paging);
    }

    /**
     * For demo purposes, populate sufficient data for paging
     */
    public void populateOrders() {
        for (int i = 0; i < 40; i++) {
            orderingRepository.save(new Orders("MY", Size.SMALL, i + 1));
        }
    }

    public ResponseEntity<String> newOrder(String country, Size size, int salesQuantity) {
        Optional<Lemon> lemonFound = lemonRepo.findBy(country, size).stream().findFirst();
        if (lemonFound.isEmpty()) {
            Tracer.warning("Sales aborted, we are running out of stock for size {0} in {1}", size, country);
            return new ResponseEntity<>("No such stock", HttpStatus.NOT_ACCEPTABLE);
        }

        Lemon lemon = lemonFound.get();
        if (lemon.getQuantity() < salesQuantity) {
            Tracer.warning("Sales aborted, we only have {2} for size {0} in {1}", size, country, lemon.getQuantity());
            return new ResponseEntity<>("Insufficient stocks, current balance: " + lemon.getQuantity(), HttpStatus.NOT_ACCEPTABLE);
        }

        // Handle order
        lemon.setQuantity(lemon.getQuantity() - salesQuantity);
        lemonRepo.save(lemon);

        Orders orders = new Orders(country, size, salesQuantity);
        orderingRepository.save(orders);

        return new ResponseEntity<>("Order submitted, ID is " + orders.getId(), HttpStatus.OK);
    }
}
