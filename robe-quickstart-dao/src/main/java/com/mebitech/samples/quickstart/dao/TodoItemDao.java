package com.mebitech.samples.quickstart.dao;

import com.tr.nebula.persistence.jpa.dao.BaseDaoImpl;
import com.mebitech.samples.quickstart.domain.TodoItem;
import org.springframework.stereotype.Service;


/**
 * Created by Mustafa Erbin on 4/8/17.
 */
@Service
public class TodoItemDao extends BaseDaoImpl<TodoItem, Long> {

}
