package com.mebitech.samples.quickstart.repository;

import com.mebitech.samples.quickstart.domain.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kamilbukum on 06/03/2017.
 */
@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    List<TodoItem> findByParent_id(Long parentOid);
}
