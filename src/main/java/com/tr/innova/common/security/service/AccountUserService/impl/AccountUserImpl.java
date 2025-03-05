package com.tr.innova.common.security.service.AccountUserService.impl;

import com.tr.innova.common.enumeration.EnumStatus;
import com.tr.innova.common.mapper.AccountUserMapper;
import com.tr.innova.common.specification.GenericSpecification;
import com.tr.innova.criteria.AccountUserCriteria;
import com.tr.innova.common.security.model.AccountRole;
import com.tr.innova.common.security.model.AccountUser;
import com.tr.innova.common.security.model.dto.AccountRoleDto;
import com.tr.innova.common.security.model.dto.AccountUserDto;
import com.tr.innova.common.security.repository.AccountRoleRepository;
import com.tr.innova.common.security.repository.AccountUserRepository;
import com.tr.innova.common.security.service.AccountUserService.AccountUserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountUserImpl implements AccountUserService {

    private final AccountUserMapper accountUserMapper;
    private final AccountUserRepository accountUserRepository;
    private final GenericSpecification<AccountUser> specifications;
    private final PasswordEncoder passwordEncoder;
    private final AccountRoleRepository accountRoleRepository;

    @Autowired
    public AccountUserImpl(AccountUserMapper accountUserMapper, AccountUserRepository accountUserRepository, PasswordEncoder passwordEncoder, AccountRoleRepository accountRoleRepository) {
        this.accountUserMapper = accountUserMapper;
        this.accountUserRepository = accountUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.specifications = new GenericSpecification<AccountUser>();
        this.accountRoleRepository = accountRoleRepository;
    }

    @Override
    public List<AccountUserDto> getAll() {
        return accountUserRepository.findAll().stream().map(accountUserMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<AccountUserDto> findById(Long aLong) {
        Optional<AccountUser> accountUser = accountUserRepository.findById(aLong);
        return accountUser.map(accountUserMapper::toDto);
    }

    @Override
    public AccountUser saveEntity(AccountUser entity) {
        return accountUserRepository.save(entity);
    }

    @Override
    public AccountUserDto save(AccountUserDto accountUserDto) {
        AccountUser accountUser = accountUserMapper.toEntity(accountUserDto);
        accountUser.setPassword(passwordEncoder.encode(accountUserDto.getPassword()));
        AccountUser savedAccountUser = accountUserRepository.save(accountUser);
        return accountUserMapper.toDto(savedAccountUser);
    }

    @Override
    public void deleteById(Long aLong) {
        Optional<AccountUser> accountUserOptional = accountUserRepository.findById(aLong);
        if (accountUserOptional.isPresent()) {
            AccountUser accountUser = accountUserOptional.get();
            accountUser.setStatus(EnumStatus.MANTIKSAL_SILINMIS);
            accountUserRepository.save(accountUser);
        }
    }

    @Override
    public AccountUserDto update(AccountUserDto accountUserDto) {
        AccountUser existingUser = accountUserRepository.findById(accountUserDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı"));

        if (accountUserDto.getUsername() != null) {
            existingUser.setUsername(accountUserDto.getUsername());
        }

        if (accountUserDto.getEmail() != null) {
            existingUser.setEmail(accountUserDto.getEmail());
        }

        if (accountUserDto.getPassword() != null && !accountUserDto.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(accountUserDto.getPassword())); //TODO
        }

        if (accountUserDto.getName() != null) {
            existingUser.setName(accountUserDto.getName());
        }

        if (accountUserDto.getSurname() != null) {
            existingUser.setSurname(accountUserDto.getSurname());
        }

        // Kullanıcının Rollerini Güncelle
        Set<AccountRole> updatedRoles = new HashSet<>();
        if (accountUserDto.getRoles() != null && !accountUserDto.getRoles().isEmpty()) {
            for (AccountRoleDto roleDto : accountUserDto.getRoles()) {
                AccountRole role = accountRoleRepository.findById(roleDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Rol bulunamadı: " + roleDto.getId()));
                updatedRoles.add(role);
            }
        }
        existingUser.setRoles(updatedRoles);

        AccountUser savedUser = accountUserRepository.save(existingUser);
        return accountUserMapper.toDto(savedUser);
    }

    @Override
    public Page<AccountUserDto> getAllByCriteria(AccountUserCriteria kullaniciCriteria, Pageable pageable) {
        Specification<AccountUser> spec = handleSpecification(kullaniciCriteria);
        return accountUserRepository.findAll(spec, pageable).map(accountUserMapper::toDto);
    }

    private Specification<AccountUser> handleSpecification(AccountUserCriteria criteria) {

        Specification<AccountUser> spec = Specification.where(null);
        if (criteria.getUsername() != null) {
            spec = spec.and(specifications.like("username", criteria.getUsername()));
        }
        if (criteria.getName() != null) {
            spec = spec.and(specifications.like("name", criteria.getName()));
        }
        if (criteria.getSurname() != null) {
            spec = spec.and(specifications.like("surname", criteria.getSurname()));
        }
        if (criteria.getEmail() != null) {
            spec = spec.and(specifications.like("email", criteria.getEmail()));
        }

        if (criteria.getStatus() != null) {
            spec = spec.and(specifications.equal("status", criteria.getStatus()));
        }

        return spec;
    }

    @Override
    public List<AccountUser> findAll() {
        return List.of();
    }

    @Override
    public AccountUserDto updateUserRoles(Long userId, List<AccountRoleDto> roles) {
        AccountUser user = accountUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı: " + userId));

        Set<AccountRole> newRoles = roles.stream()
                .map(roleDto -> accountRoleRepository.findById(roleDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Rol bulunamadı: " + roleDto.getId())))
                .collect(Collectors.toSet());

        user.setRoles(newRoles);
        AccountUser savedUser = accountUserRepository.save(user);
        return accountUserMapper.toDto(savedUser);
    }

}
