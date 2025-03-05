package com.tr.innova.common.security.model.dto;

import com.tr.innova.common.security.model.AccountUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private Long id;

    private String username;

    private String email;

    private String password;

    @Getter
    private Collection<? extends GrantedAuthority> grants;

    @Getter
    private Collection<? extends GrantedAuthority> roles;

    // Constructor
    public UserDetailsImpl(Long id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> roles, Collection<? extends GrantedAuthority> grants) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;

        this.roles = new ArrayList<>(roles);
        this.grants = new ArrayList<>(grants);
    }

    public static UserDetailsImpl build(AccountUser user) {

        // Rolleri ve grant'lerini al
        List<GrantedAuthority> roles = new ArrayList<>();
        List<GrantedAuthority> grants = new ArrayList<>();

        user.getRoles().forEach(role -> {
            // Rol kodlarını ekle
            roles.add(new SimpleGrantedAuthority(role.getCode()));
            role.getGrants().forEach(grant -> {
                grants.add(new SimpleGrantedAuthority(grant.getCode()));
            });
        });
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                roles,
                grants);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> combinedAuthorities = new ArrayList<>();
        combinedAuthorities.addAll(roles);
        combinedAuthorities.addAll(grants);
        return combinedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
