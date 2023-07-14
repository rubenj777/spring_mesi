package com.project.mesi.service;

import com.project.mesi.dto.ProductDto;
import com.project.mesi.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService
{

    void save(ProductDto productDto, @AuthenticationPrincipal UserDetails userDetails, MultipartFile file) throws IOException;

    Product findOneById(Long id);

    List<Product> findByCategoryName(String name);

    public Page<Product> findPaginated(int pageNo, int pageSize);

    public Page<Product> findPaginated(int pageNo, int pageSize, String name);

}
