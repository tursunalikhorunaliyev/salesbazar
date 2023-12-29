package com.hamkor.salesbazar.service;

import com.hamkor.salesbazar.dto.request.HamkorClientDTO;
import com.hamkor.salesbazar.dto.response.ResponseData;
import com.hamkor.salesbazar.entity.ClientBalance;
import com.hamkor.salesbazar.entity.HamkorClient;
import com.hamkor.salesbazar.entity.UserData;
import com.hamkor.salesbazar.repository.ClientBalanceRepository;
import com.hamkor.salesbazar.repository.HamkorClientRepository;
import com.hamkor.salesbazar.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class HamkorClientService {
    private final JWTGenerator jwtGenerator;
    private final HamkorClientRepository hamkorClientRepository;
    private final ClientBalanceRepository clientBalanceRepository;

    public ResponseEntity<ResponseData> createClient(HamkorClientDTO dto, HttpServletRequest request){
        UserData userData = jwtGenerator.getUserFromRequest(request);
        HamkorClient hamkorClient = new HamkorClient();
        hamkorClient.setFirstname(dto.getFirstname());
        hamkorClient.setLastname(dto.getLastname());
        hamkorClient.setPhone(dto.getPhone());
        hamkorClient.setBusinessName(dto.getBusinessName());
        hamkorClient.setUser(userData);

        try {
            hamkorClientRepository.save(hamkorClient);
            ClientBalance clientBalance = new ClientBalance();
            clientBalance.setHamkorClient(hamkorClient);
            clientBalance.setLeftAmount(0);
            clientBalance.setTotalAmount(0);
            clientBalance.setPaidAmount(0);
            clientBalanceRepository.save(clientBalance);
            return ResponseEntity.ok(new ResponseData(true, "Client yaratildi", null));
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(new ResponseData(false, "Allaqachon mavjud", null), HttpStatus.BAD_REQUEST);
        }

    }
    public ResponseEntity<ResponseData> getAll(HttpServletRequest request){
        UserData userData = jwtGenerator.getUserFromRequest(request);
        boolean isAdmin = jwtGenerator.isAdmin(userData);
        if(isAdmin){
            return ResponseEntity.ok(new ResponseData(true, "Barcha klientlar", hamkorClientRepository.findAllClient()));
        }
        else{
            return new ResponseEntity<>(new ResponseData(false, "Siz admin emassiz", null), HttpStatus.BAD_REQUEST);
        }
    }
}
