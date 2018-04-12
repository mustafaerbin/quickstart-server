package com.mebitech.samples.quickstart.dao;

import com.tr.nebula.persistence.jpa.dao.BaseDaoImpl;
import com.mebitech.samples.quickstart.domain.Todo;
import org.springframework.stereotype.Service;


/**
 * Created by recepkoseoglu on 4/8/17.
 */
@Service
public class TodoDao extends BaseDaoImpl<Todo, Long> {

}
