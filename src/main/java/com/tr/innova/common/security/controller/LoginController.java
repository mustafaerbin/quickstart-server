package com.tr.innova.common.security.controller;

import com.tr.innova.common.security.model.dto.JwtResponse;
import com.tr.innova.common.security.model.dto.LoginRequest;
import com.tr.innova.common.security.model.dto.UserDetailsImpl;
import com.tr.innova.common.security.service.AuthenticationService;
import com.tr.innova.common.security.service.SessionInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Controller
public class LoginController {

    private final AuthenticationService authenticationService;
    private final SessionInfo sessionInfo;

    public LoginController(AuthenticationService authenticationService, SessionInfo sessionInfo) {
        this.authenticationService = authenticationService;
        this.sessionInfo = sessionInfo;
    }


    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam(value = "logout", required = false) String logout, Model model) {
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return new ResponseEntity<>(OK);
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        try {
            // Kimlik doğrulama işlemi
            authenticationService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

            String jwt = authenticationService.createToken(loginRequest.getUsername());

            UserDetailsImpl userDetails = sessionInfo.getSessionInfo();

            // Roller ve izinler
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .filter(role -> role.startsWith("ROLE_"))
                    .toList();

            List<String> grants = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .filter(role -> role.startsWith("GRANT_"))
                    .toList();

            // Başarılı login yanıtı
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), roles, grants));

        } catch (BadCredentialsException e) {
            // Hatalı kimlik bilgisi
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestBody LoginRequest loginRequest) {
        // Authentication işlemi

        Authentication authenticate = authenticationService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        String jwt = authenticationService.createToken(loginRequest.getUsername());

        UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(role -> role.startsWith("ROLE_"))
                .toList();


        List<String> grants = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(role -> role.startsWith("GRANT_"))
                .toList();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), roles, grants));
    }

}
