package com.github.shuntak.entity.dao;

import com.github.shuntak.entity.Item;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class ItemDao extends AbstractDAO<Item> {
    public ItemDao(SessionFactory factory) {
        super(factory);
    }
}
