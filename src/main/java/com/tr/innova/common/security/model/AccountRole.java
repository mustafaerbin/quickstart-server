package com.tr.innova.common.security.model;

import com.tr.innova.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "account_role")
public class AccountRole extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", length = 100, nullable = false, unique = true)
    private String code;

    @Column(name = "description", length = 1000)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "account_role_grant",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "grant_id")
    )
    private Set<AccountGrant> grants;

}
