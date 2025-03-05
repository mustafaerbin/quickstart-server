package com.tr.innova.criteria;

import com.tr.innova.common.security.model.AccountGrant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRoleCriteria implements Serializable {
    private Long id;
    private String name;
    private String code;
    private String description;
    private Set<AccountGrant> grants;
}

