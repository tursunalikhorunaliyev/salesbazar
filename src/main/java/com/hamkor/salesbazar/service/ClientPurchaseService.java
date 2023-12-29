package com.hamkor.salesbazar.service;

import com.hamkor.salesbazar.dto.request.ClientPurchaseDTO;
import com.hamkor.salesbazar.dto.response.ResponseData;
import com.hamkor.salesbazar.entity.*;
import com.hamkor.salesbazar.repository.*;
import com.hamkor.salesbazar.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@AllArgsConstructor
public class ClientPurchaseService {

    private final HamkorClientRepository hamkorClientRepository;
    private final ProductForSaleRepository productForSaleRepository;
    private final PaymentIntervalRepository paymentIntervalRepository;
    private final WeekDayRepository weekDayRepository;
    private final JWTGenerator jwtGenerator;
    private final HamkorBazarRepository hamkorBazarRepository;
    private final ClientBalanceRepository clientBalanceRepository;

    public ResponseEntity<ResponseData> create(ClientPurchaseDTO dto, HttpServletRequest request) {

        Integer totalPrice = 0;
        ClientPurchase clientPurchase = new ClientPurchase();
        Optional<HamkorClient> hamkorClient = hamkorClientRepository.findById(dto.getClient_id());
        if (hamkorClient.isEmpty()) {
            return new ResponseEntity<>(new ResponseData(false, "Klient topilmadi", null), HttpStatus.BAD_REQUEST);
        }
        clientPurchase.setHamkorClient(hamkorClient.get());
        if (dto.getProducts().length() == 1) {
            Optional<ProductForSale> product = productForSaleRepository.findById(Long.parseLong(dto.getProducts()));
            if (product.isEmpty()) {
                return new ResponseEntity<>(new ResponseData(false, "Product topilmadi", null), HttpStatus.BAD_REQUEST);
            }
            clientPurchase.setProductsForSale(Collections.singleton(product.get()));
            clientPurchase.setTotalPrice(product.get().getTotalPrice());
            clientPurchase.setTotalAmount(product.get().getTotalPrice());
            clientPurchase.setPaidAmount(0);
            clientPurchase.setLeftAmount(0);
        } else {
            Set<ProductForSale> products = productForSaleRepository.getByInIds(Arrays.stream(dto.getProducts().split(",")).map(Long::parseLong).toList());
            if (products.size() != dto.getProducts().split(",").length) {
                return new ResponseEntity<>(new ResponseData(false, "Ma'lumotlar topilmadi", null), HttpStatus.BAD_REQUEST);
            }
            clientPurchase.setProductsForSale(products);
            List<Integer> totalProductPrice = products.stream().map(ProductForSale::getTotalPrice).toList();

            for (Integer price : totalProductPrice) {
                totalPrice += price;
            }
            clientPurchase.setTotalPrice(totalPrice);
            clientPurchase.setTotalAmount(totalPrice);
            clientPurchase.setPaidAmount(0);
            clientPurchase.setLeftAmount(0);
        }
        Optional<PaymentInterval> paymentInterval = paymentIntervalRepository.findById(dto.getInterval_id());

        if (paymentInterval.isEmpty()) {
            return new ResponseEntity<>(new ResponseData(false, "Ma'lumotlar topilmadi", null), HttpStatus.BAD_REQUEST);
        }
        clientPurchase.setPaymentInterval(paymentInterval.get());
        clientPurchase.setNormalPayment(dto.getNormal_payment());
        if (dto.getWeek_day() != null) {
            Optional<WeekDay> weekDay = weekDayRepository.findById(dto.getWeek_day());
            if (weekDay.isEmpty()) {
                return new ResponseEntity<>(new ResponseData(false, "Hafta kuni topilmadi", null), HttpStatus.BAD_REQUEST);
            }
            clientPurchase.setWeekDay(weekDay.get());
        }
        clientPurchase.setUser(jwtGenerator.getUserFromRequest(request));
        if(hamkorBazarRepository.countFirstBy()==0){
            HamkorBazar hamkorBazar = new HamkorBazar();
            hamkorBazar.setLeftAmount(0);
            hamkorBazar.setCurrentAmount(0);
            hamkorBazar.setIncomeAmount(0);
        }
        else{
            HamkorBazar hamkorBazar = hamkorBazarRepository.findById(1L).get();
            hamkorBazar.setCurrentAmount(hamkorBazar.getCurrentAmount()+totalPrice);
            hamkorBazarRepository.save(hamkorBazar);
        }
        ClientBalance clientBalance = clientBalanceRepository.findByHamkorClient(hamkorClient.get());
        clientBalance.setTotalAmount(clientBalance.getTotalAmount()+totalPrice);
        clientBalance.setLeftAmount(clientPurchase.getLeftAmount()+totalPrice);
        clientBalanceRepository.save(clientBalance);



        return ResponseEntity.ok(new ResponseData(true, "Barcha ma'lumotlar saqlandi", null));

    }
}
