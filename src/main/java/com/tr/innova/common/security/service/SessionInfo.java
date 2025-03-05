package com.tr.innova.common.security.service;

import com.tr.innova.common.security.model.dto.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SessionInfo {

    /**
     * Kullanıcı oturum bilgilerini doldurarak UserSessionDTO döner.
     */
    public UserDetailsImpl getSessionInfo() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {

            return (UserDetailsImpl) authentication.getPrincipal();
        }

        return null;
    }

    private String getUsername(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    public List<String> getRoles(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth -> auth.startsWith("ROLE_")) // Eğer roller "ROLE_" ile başlıyorsa filtreleme
                .collect(Collectors.toList());
    }

    public List<String> getPermissions() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth -> !auth.startsWith("ROLE_")) // Roller dışındaki izinleri almak
                .collect(Collectors.toList());
    }
}
