package com.tr.innova.common.security.service.AccountRoleService.impl;

import com.tr.innova.common.enumeration.EnumStatus;
import com.tr.innova.common.mapper.AccountRoleMapper;
import com.tr.innova.common.specification.GenericSpecification;
import com.tr.innova.criteria.AccountRoleCriteria;
import com.tr.innova.common.security.model.AccountRole;
import com.tr.innova.common.security.model.dto.AccountRoleDto;
import com.tr.innova.common.security.repository.AccountRoleRepository;
import com.tr.innova.common.security.repository.AccountUserRepository;
import com.tr.innova.common.security.service.AccountRoleService.AccountRoleService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountRoleImpl implements AccountRoleService {

    private final AccountRoleMapper accountRoleMapper;
    private final AccountRoleRepository accountRoleRepository;
    private final GenericSpecification<AccountRole> specifications;
    private final AccountUserRepository accountUserRepository;

    public AccountRoleImpl(AccountRoleMapper accountRoleMapper, AccountRoleRepository accountRoleRepository, AccountUserRepository accountUserRepository) {
        this.accountRoleMapper = accountRoleMapper;
        this.accountRoleRepository = accountRoleRepository;
        this.accountUserRepository = accountUserRepository;
        this.specifications = new GenericSpecification<AccountRole>();
    }

    @Override
    public List<AccountRole> findAll() {
        return accountRoleRepository.findAll();
    }

    @Override
    public List<AccountRoleDto> getAll() {
        return this.findAll().stream().map(accountRoleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<AccountRoleDto> findById(Long aLong) {
        Optional<AccountRole> accountRole = accountRoleRepository.findById(aLong);
        return accountRole.map(accountRoleMapper::toDto);
    }

    @Override
    public AccountRole saveEntity(AccountRole entity) {
        return accountRoleRepository.save(entity);
    }

    @Override
    public AccountRoleDto save(AccountRoleDto accountRoleDto) {
        AccountRole accountRole = accountRoleMapper.toEntity(accountRoleDto);
        this.saveEntity(accountRole);
        return accountRoleMapper.toDto(accountRole);
    }

    @Override
    public void deleteById(Long aLong) {
        Optional<AccountRole> accountRoleOptional = accountRoleRepository.findById(aLong);
        if (accountRoleOptional.isPresent()) {
            AccountRole role = accountRoleOptional.get();
            role.setStatus(EnumStatus.MANTIKSAL_SILINMIS);
            accountRoleRepository.save(role);
        }
    }

    @Override
    public AccountRoleDto update(AccountRoleDto accountRoleDto) {
        return null;
    }

    @Override
    public Page<AccountRoleDto> getAllByCriteria(AccountRoleCriteria roleCriteria, Pageable pageable) {
        Specification<AccountRole> spec = handleSpecification(roleCriteria);
        return accountRoleRepository.findAll(spec, pageable).map(accountRoleMapper::toDto);
    }

    private Specification<AccountRole> handleSpecification(AccountRoleCriteria criteria) {
        Specification<AccountRole> spec = Specification.where(null);
        if (criteria.getName() != null) {
            spec = spec.and(specifications.like("name", criteria.getName()));
        }
        if (criteria.getCode() != null) {
            spec = spec.and(specifications.like("code", criteria.getCode()));
        }
        if (criteria.getDescription() != null) {
            spec = spec.and(specifications.like("description", criteria.getDescription()));
        }
        if (criteria.getGrants() != null && !criteria.getGrants().isEmpty()) {
            spec = spec.and(((root, query, criteriaBuilder) ->
                    root.get("grants").in(criteria.getGrants())));
        }

        return spec;
    }
}
