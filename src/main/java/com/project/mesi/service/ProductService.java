package com.project.mesi.service;

import com.project.mesi.dto.ProductDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

public interface ProductService
{

    void save(ProductDto productDto, @AuthenticationPrincipal UserDetails userDetails);

}
