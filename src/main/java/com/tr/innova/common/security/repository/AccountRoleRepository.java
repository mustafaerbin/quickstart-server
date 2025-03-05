package com.tr.innova.common.security.repository;

import com.tr.innova.common.security.model.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AccountRoleRepository extends RevisionRepository<AccountRole, Long, Long>,
        JpaRepository<AccountRole, Long>, JpaSpecificationExecutor<AccountRole> {

}
