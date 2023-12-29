package com.hamkor.salesbazar.service;

import com.hamkor.salesbazar.dto.request.ProductForSaleDTO;
import com.hamkor.salesbazar.dto.response.ResponseData;
import com.hamkor.salesbazar.entity.Product;
import com.hamkor.salesbazar.entity.ProductForSale;
import com.hamkor.salesbazar.entity.UserData;
import com.hamkor.salesbazar.repository.ProductForSaleRepository;
import com.hamkor.salesbazar.repository.ProductRepository;
import com.hamkor.salesbazar.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductForSaleService {
    private final JWTGenerator jwtGenerator;
    private final ProductForSaleRepository productForSaleRepository;
    private final ProductRepository productRepository;

    public ResponseEntity<ResponseData> create(ProductForSaleDTO dto, HttpServletRequest request) {
        UserData userData = jwtGenerator.getUserFromRequest(request);
        ProductForSale productForSale = new ProductForSale();
        Optional<Product> product = productRepository.findById(dto.getProduct_id());
        if (product.isEmpty()) {
            return new ResponseEntity<>(new ResponseData(false, "Bunday maxsulot topilmadi", null), HttpStatus.BAD_REQUEST);
        }
        productForSale.setProduct(product.get());
        productForSale.setProductPrice(dto.getProduct_price());
        productForSale.setTotalPrice(dto.getCount() * dto.getProduct_price());
        productForSale.setCount(dto.getCount());
        productForSale.setUser(userData);
        productForSaleRepository.save(productForSale);
        return ResponseEntity.ok(new ResponseData(true, "Ma'lumotlar saqlandi", productForSale));

    }

    public ResponseEntity<ResponseData> delete(Long id) {
        if (productForSaleRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
        return ResponseEntity.ok(new ResponseData(true, "Ma'lumot o'chirib yuborildi", null));
    }
}
