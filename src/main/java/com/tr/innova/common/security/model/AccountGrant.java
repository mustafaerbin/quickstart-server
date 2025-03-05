package com.tr.innova.common.security.model;

import com.tr.innova.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "account_grant")
@Entity
public class AccountGrant extends BaseEntity {

    @Column(name = "code", length = 100, nullable = false, unique = true)
    private String code;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    private String permission;

}
