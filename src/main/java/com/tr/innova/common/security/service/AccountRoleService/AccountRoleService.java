package com.tr.innova.common.security.service.AccountRoleService;


import com.tr.innova.criteria.AccountRoleCriteria;
import com.tr.innova.common.security.model.AccountRole;
import com.tr.innova.common.security.model.dto.AccountRoleDto;
import com.tr.innova.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccountRoleService extends BaseService<AccountRoleDto, AccountRole, Long> {

    @Override
    List<AccountRoleDto> getAll();

    @Override
    Optional<AccountRoleDto> findById(Long aLong);

    @Override
    AccountRole saveEntity(AccountRole entity);

    @Override
    AccountRoleDto save(AccountRoleDto accountRoleDto);

    @Override
    void deleteById(Long aLong);

    AccountRoleDto update(AccountRoleDto accountRoleDto);

    Page<AccountRoleDto> getAllByCriteria(AccountRoleCriteria accountRoleCriteria, Pageable pageable);
}
