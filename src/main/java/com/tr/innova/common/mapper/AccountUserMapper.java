package com.tr.innova.common.mapper;

import com.tr.innova.common.security.model.AccountUser;
import com.tr.innova.common.security.model.dto.AccountUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
        AccountRoleMapper.class,
})
public interface AccountUserMapper extends EntityMapper<AccountUserDto, AccountUser> {

    AccountUserMapper INSTANCE = Mappers.getMapper(AccountUserMapper.class);

    AccountUser toEntity(AccountUserDto accountUserDto);

    AccountUserDto toDto(AccountUser accountUser);

}
