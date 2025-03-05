package com.tr.innova.common.mapper;

import com.tr.innova.common.security.model.AccountRole;
import com.tr.innova.common.security.model.dto.AccountRoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
        AccountUserMapper.class,
})
public interface AccountRoleMapper extends EntityMapper<AccountRoleDto, AccountRole> {
    AccountRoleMapper INSTANCE = Mappers.getMapper(AccountRoleMapper.class);

    AccountRole toEntity(AccountRoleDto accountRoleDto);

    AccountRoleDto toDto(AccountRole accountRole);

}
