package com.github.shuntak.entity.dao;

import com.github.shuntak.entity.Item;
import io.dropwizard.hibernate.AbstractDAO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;

import java.util.List;

public class ItemDao extends AbstractDAO<Item> {
    public ItemDao(SessionFactory factory) {
        super(factory);
    }

    public List<Object> find(String targetItemId) {
        StringBuilder query = new StringBuilder("from Item");
        boolean first = true;

        if (!StringUtils.isEmpty(targetItemId)) {
            if (first) {
                query.append(" where");
                first = false;
            } else {
                query.append(" and");
            }
            query.append(" itemId = '");
            query.append(targetItemId);
            query.append("'");
        }

        return currentSession()
                .createQuery(query.toString())
                .list();
    }

    public void update(Item item) {
        currentSession().update(item);
    }
}
