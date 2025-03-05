package com.tr.innova.common.security.model.dto;

import com.tr.innova.common.security.model.AccountGrant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRoleDto implements Serializable {
    private Long id;
    private String name;
    private String code;
    private String description;
    private Set<AccountGrant> grants;
}
