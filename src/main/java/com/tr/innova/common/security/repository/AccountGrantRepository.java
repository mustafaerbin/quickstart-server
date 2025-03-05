package com.tr.innova.common.security.repository;

import com.tr.innova.common.security.model.AccountGrant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountGrantRepository extends JpaRepository<AccountGrant, Long> {
}

