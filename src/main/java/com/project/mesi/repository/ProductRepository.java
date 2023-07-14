package com.project.mesi.repository;

import com.project.mesi.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByIdProduct(Long id);

    List<Product> findAllByIdProduct(Long id, Pageable pageable);

    Page<Product> findAllByCategory_Name(String name, Pageable pageable);

}
