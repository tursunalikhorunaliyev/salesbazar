package com.hamkor.salesbazar.service;

import com.hamkor.salesbazar.dto.request.ProductDTO;
import com.hamkor.salesbazar.dto.response.ResponseData;
import com.hamkor.salesbazar.entity.Product;
import com.hamkor.salesbazar.entity.UserData;
import com.hamkor.salesbazar.repository.ProductRepository;
import com.hamkor.salesbazar.repository.ProductTypeRepository;
import com.hamkor.salesbazar.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class ProductService {
    private final JWTGenerator jwtGenerator;
    private final ProductTypeRepository productTypeRepository;
    private final ProductRepository productRepository;

    public ResponseEntity<ResponseData> create(ProductDTO dto, HttpServletRequest request) {
        UserData userData = jwtGenerator.getUserFromRequest(request);
        Product product = new Product();
        product.setProductType(productTypeRepository.findById(dto.getProduct_type_id()).get());
        product.setName(dto.getName());
        product.setUser(userData);
        try {
            productRepository.save(product);
            return ResponseEntity.ok(new ResponseData(true, "Product yaratildi", null));
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(new ResponseData(false, "Allaqachon mavjud", null), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ResponseData> getAll() {
        return ResponseEntity.ok(new ResponseData(true, "Barcha productlar", productRepository.findAllProduct()));
    }
}
