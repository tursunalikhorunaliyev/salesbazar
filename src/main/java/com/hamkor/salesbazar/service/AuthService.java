package com.hamkor.salesbazar.service;

import com.hamkor.salesbazar.dto.response.ResponseData;
import com.hamkor.salesbazar.entity.Role;
import com.hamkor.salesbazar.repository.RoleRepository;
import com.hamkor.salesbazar.entity.UserData;
import com.hamkor.salesbazar.repository.UserDataRepository;
import com.hamkor.salesbazar.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class AuthService {
    private final JWTGenerator jwtGenerator;
    private final UserDataRepository userDataRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;


    public ResponseEntity<ResponseData> registerUser(String username, String password){


        if (userDataRepository.existsByUsername(username)) {
            return new ResponseEntity<>(new ResponseData(false, "Bu foydalanuvchi mavjud!", null), HttpStatus.BAD_REQUEST);
        }

        final UserData user = new UserData();
        Role role = roleRepository.findByName("ADMIN").orElse(null);
        if (role == null) {
            final Role forSaveRole = new Role();
            forSaveRole.setName("ADMIN");
            role = roleRepository.save(forSaveRole);
        }

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Collections.singleton(role));
        userDataRepository.save(user);
        return new ResponseEntity<>(new ResponseData(true, "Foydalanuvchi ro'yhatdan o'tdi!", null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseData> loginUser(String username, String password){
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new ResponseData(true, "Foydalanuvchi tizimga kirdi", token), HttpStatus.OK);
    }
}

