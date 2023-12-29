package com.hamkor.salesbazar.controller;

import com.hamkor.salesbazar.dto.request.HamkorClientDTO;
import com.hamkor.salesbazar.dto.response.ResponseData;
import com.hamkor.salesbazar.service.HamkorClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("api/clients")
@AllArgsConstructor
public class ClientController {
    private final HamkorClientService hamkorClientService;
    @PostMapping("/new")
    public ResponseEntity<ResponseData> create(@RequestBody @Valid HamkorClientDTO clientDTO, HttpServletRequest request){
        return hamkorClientService.createClient(clientDTO, request);
    }
    @GetMapping("/")
    public ResponseEntity<ResponseData> getAll(HttpServletRequest request){
        return hamkorClientService.getAll(request);
    }
}
