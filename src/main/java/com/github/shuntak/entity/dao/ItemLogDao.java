package com.github.shuntak.entity.dao;

import com.github.shuntak.entity.ItemLog;
import io.dropwizard.hibernate.AbstractDAO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;

import java.util.List;

public class ItemLogDao extends AbstractDAO<ItemLog> {
    public ItemLogDao(SessionFactory factory) {
        super(factory);
    }

    public void save(ItemLog log) {
        currentSession().save(log);
    }

    public List<Object> find(String targetItemId) {
        StringBuilder query = new StringBuilder("from ItemLog");
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

        query.append(" order by logDateTime desc");

        return currentSession()
                .createQuery(query.toString())
                .setMaxResults(20)
                .list();
    }
}
