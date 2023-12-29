package com.hamkor.salesbazar.service;

import com.hamkor.salesbazar.dto.response.ResponseData;
import com.hamkor.salesbazar.entity.ProductType;
import com.hamkor.salesbazar.entity.UserData;
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
public class ProductTypeService {
    private final JWTGenerator jwtGenerator;
    private final ProductTypeRepository productTypeRepository;

    public ResponseEntity<ResponseData> create(String name, HttpServletRequest request){
        UserData userData = jwtGenerator.getUserFromRequest(request);
        ProductType productType = new ProductType();
        productType.setName(name);
        productType.setUser(userData);
        try {
            productTypeRepository.save(productType);
            return ResponseEntity.ok(new ResponseData(true, "Ma'lumot saqlandi", null));
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(new ResponseData(false, "Allaqachon mavjud", null), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<ResponseData> getAll(){
        return ResponseEntity.ok(new ResponseData(true, "Barcha product turlari", productTypeRepository.findAllProductType()));
    }
}
