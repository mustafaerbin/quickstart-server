package com.tr.innova.common.security.controller;

import com.tr.innova.common.security.model.dto.UserDetailsImpl;
import com.tr.innova.common.security.service.AccountUserService.AccountUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LogManager.getLogger(AuthController.class);

    private final AccountUserService accountUserService;

    public AuthController(AccountUserService accountUserService) {
        this.accountUserService = accountUserService;
    }

    @GetMapping("/currentUser")
    public ResponseEntity<?> getCurrentUser() {

        UserDetailsImpl currentUser = getCurrentUserDetails();
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Kullanıcı oturum açmamış!");
        }
        return ResponseEntity.ok(currentUser);
    }

    public UserDetailsImpl getCurrentUserDetails() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() &&
                authentication.getPrincipal() instanceof UserDetailsImpl) {
            return (UserDetailsImpl) authentication.getPrincipal();
        }
        throw new RuntimeException("Kullanıcı bilgisi bulunamadı!");
    }

    @GetMapping("/init")
    public String init() {
        return "ok";
    }

    @GetMapping("/log")
    public void log() throws IOException {
        int result = 10 / 0;

    }

    // http://localhost:8080/auth/insert
    @GetMapping("/insert")
    public ResponseEntity<String> insert() throws IOException {
        return new ResponseEntity<>(OK);
    }

}


