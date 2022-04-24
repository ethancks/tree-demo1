package com.lemon.tree.db.repository;

import com.lemon.tree.db.model.Orders;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderingRepository extends PagingAndSortingRepository<Orders, Long> {
}
