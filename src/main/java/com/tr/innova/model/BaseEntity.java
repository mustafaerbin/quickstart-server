package com.tr.innova.model;

import com.tr.innova.common.enumeration.EnumStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.io.Serializable;
import java.time.Instant;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CREATE_DATE")
    private Instant createDate;

    @Column(name = "UPDATE_DATE")
    private Instant updateDate;

    @Version
    private Long version;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private EnumStatus status;

    @PrePersist
    public void onPrePersist() {
        setCreateDate(Instant.now());
        setStatus(EnumStatus.AKTIF);
    }

    @PreUpdate
    public void onPreUpdate() {
        setUpdateDate(Instant.now());
    }
}

