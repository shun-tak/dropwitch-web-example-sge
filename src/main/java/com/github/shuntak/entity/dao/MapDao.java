package com.github.shuntak.entity.dao;

import com.github.shuntak.entity.Map;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class MapDao extends AbstractDAO<Map> {
    public MapDao(SessionFactory factory) {
        super(factory);
    }
}
