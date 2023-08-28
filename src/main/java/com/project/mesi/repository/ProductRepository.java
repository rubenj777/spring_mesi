package com.project.mesi.repository;

import com.project.mesi.entity.Category;
import com.project.mesi.entity.Product;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByIdProduct(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.name = ?1, p.price = ?2, p.description = ?3, p.category = ?4 WHERE p.id = ?5")
    void updateProduct(String name, int price, String description, Category category, Long productId);

    List<Product> findAllByIdProduct(Long id, Pageable pageable);

    Page<Product> findAllByCategory_Name(String name, Pageable pageable);

}
