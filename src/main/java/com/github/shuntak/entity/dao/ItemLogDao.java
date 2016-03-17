package com.github.shuntak.entity.dao;

import com.github.shuntak.entity.ItemLog;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class ItemLogDao extends AbstractDAO<ItemLog> {
    public ItemLogDao(SessionFactory factory) {
        super(factory);
    }

    public void save(ItemLog log) {
        currentSession().save(log);
    }
}
