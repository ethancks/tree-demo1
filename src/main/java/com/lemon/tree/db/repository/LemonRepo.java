package com.lemon.tree.db.repository;

import com.lemon.tree.db.model.Lemon;
import com.lemon.tree.db.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LemonRepo extends JpaRepository<Lemon, String> {

    @Query("FROM Lemon WHERE country = :country")
    List<Lemon> findBy(@Param("country") String country);
    @Query("FROM Lemon WHERE country = :country AND size = :size")
    List<Lemon> findBy(@Param("country") String country, @Param("size") Size size);
}
