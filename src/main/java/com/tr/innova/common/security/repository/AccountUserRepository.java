package com.tr.innova.common.security.repository;

import com.tr.innova.common.security.model.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface AccountUserRepository extends RevisionRepository<AccountUser, Long, Long>,
        JpaRepository<AccountUser, Long>, JpaSpecificationExecutor<AccountUser> {

//    @EntityGraph(value = "AccountUser.full", type = EntityGraph.EntityGraphType.FETCH)
    Optional<AccountUser> findByUsername(@Param("username") String username);

}

