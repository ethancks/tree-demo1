package com.lemon.tree.service;

import com.lemon.tree.db.model.Lemon;
import com.lemon.tree.db.model.Size;
import com.lemon.tree.db.repository.LemonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PurchasingService {
    @Autowired
    private LemonRepo lemonRepo;

    public Lemon addStock(String country, Size size, int addInQuantity) {
        Optional<Lemon> lemonFound = lemonRepo.findBy(country, size).stream().findFirst();
        lemonFound.ifPresent(lemon -> lemon.setQuantity(lemon.getQuantity() + addInQuantity));
        Lemon lemon = lemonFound.orElseGet(() -> new Lemon(country, size, addInQuantity));

        lemonRepo.save(lemon);
        return lemon;
    }
}
