package com.tr.innova.criteria;

import com.tr.innova.common.enumeration.EnumStatus;
import com.tr.innova.common.security.model.AccountRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountUserCriteria implements Serializable {

    private String name;

    private String username;

    private String surname;

    private EnumStatus status;

    private String email;

    private Instant startDate;

    private Set<AccountRole> roles;

}
