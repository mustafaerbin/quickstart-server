package com.tr.innova.common.specification;

import org.springframework.data.jpa.domain.Specification;

public class GenericSpecification<T> {

    public Specification<T> like(String attribute, String value) {
        return (root, query, cb) -> {
            if (value == null || value.trim().isEmpty()) {
                return cb.conjunction();
            }
            return cb.like(root.get(attribute), "%" + value + "%");
        };
    }

    public Specification<T> equal(String attribute, Object value) {
        return (root, query, cb) -> {
            if (value == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get(attribute), value);
        };
    }

    public Specification<T> notEqual(String attribute, Object value) {
        return (root, query, cb) -> {
            if (value == null) {
                return cb.conjunction();
            }
            return cb.notEqual(root.get(attribute), value);
        };
    }

    public <Y extends Comparable<? super Y>> Specification<T> greaterThan(String attribute, Y value) {
        return (root, query, cb) -> {
            if (value == null) {
                return cb.conjunction();
            }
            return cb.greaterThan(root.get(attribute), value);
        };
    }

    public <Y extends Comparable<? super Y>> Specification<T> lessThan(String attribute, Y value) {
        return (root, query, cb) -> {
            if (value == null) {
                return cb.conjunction();
            }
            return cb.lessThan(root.get(attribute), value);
        };
    }
}
