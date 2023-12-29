package com.hamkor.salesbazar.service;

import com.hamkor.salesbazar.dto.response.ResponseData;
import com.hamkor.salesbazar.entity.PaymentInterval;
import com.hamkor.salesbazar.repository.PaymentIntervalRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentIntervalService {

    private final PaymentIntervalRepository paymentIntervalRepository;

    public ResponseEntity<ResponseData> create(String name){
        PaymentInterval paymentInterval = new PaymentInterval();
        paymentInterval.setName(name);
        try {
            paymentIntervalRepository.save(paymentInterval);
            return ResponseEntity.ok(new ResponseData(true, "Ma'lumotlar saqlandi", null));
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(new ResponseData(false, "Allaqachon mavjud", null), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<ResponseData> all(){
        return ResponseEntity.ok(new ResponseData(true, "Barcha intervallar", paymentIntervalRepository.findAll()));
    }
}
