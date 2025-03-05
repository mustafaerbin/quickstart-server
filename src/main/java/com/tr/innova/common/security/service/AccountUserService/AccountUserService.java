package com.tr.innova.common.security.service.AccountUserService;

import com.tr.innova.criteria.AccountUserCriteria;
import com.tr.innova.common.security.model.AccountUser;
import com.tr.innova.common.security.model.dto.AccountRoleDto;
import com.tr.innova.common.security.model.dto.AccountUserDto;
import com.tr.innova.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccountUserService extends BaseService<AccountUserDto, AccountUser, Long> {

    List<AccountUserDto> getAll();

    @Override
    Optional<AccountUserDto> findById(Long aLong);

    @Override
    AccountUser saveEntity(AccountUser entity);

    @Override
    AccountUserDto save(AccountUserDto accountUserDto);

    @Override
    void deleteById(Long aLong);

    AccountUserDto update(AccountUserDto accountUserDto);

    AccountUserDto updateUserRoles(Long id, List<AccountRoleDto> roles);

    Page<AccountUserDto> getAllByCriteria(AccountUserCriteria accountUserCriteria, Pageable pageable);

}
