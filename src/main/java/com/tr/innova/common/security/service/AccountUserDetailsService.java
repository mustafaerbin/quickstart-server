package com.tr.innova.common.security.service;

import com.tr.innova.common.security.model.AccountUser;
import com.tr.innova.common.security.model.dto.UserDetailsImpl;
import com.tr.innova.common.security.repository.AccountUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountUserDetailsService implements UserDetailsService {

    private final AccountUserRepository accountUserRepository;

    public AccountUserDetailsService(AccountUserRepository accountUserRepository) {
        this.accountUserRepository = accountUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        AccountUser accountUser = accountUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserDetailsImpl build = UserDetailsImpl.build(accountUser);

        return build;
    }

}

