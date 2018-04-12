package com.mebitech.samples.quickstart.repository;

import com.mebitech.samples.quickstart.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kamilbukum on 06/03/2017.
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
