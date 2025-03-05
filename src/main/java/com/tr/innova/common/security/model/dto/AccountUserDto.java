package com.tr.innova.common.security.model.dto;

import com.tr.innova.common.enumeration.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountUserDto implements Serializable {

    private Long id;

    private String password;

    private String username;

    private String name;

    private String surname;

    private String email;

    private EnumStatus Status;

    private List<AccountRoleDto> roles;

    private Set<Long> firmaIds;

}
