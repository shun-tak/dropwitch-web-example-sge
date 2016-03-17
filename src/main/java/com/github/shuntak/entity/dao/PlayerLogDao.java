package com.github.shuntak.entity.dao;

import com.github.shuntak.entity.PlayerLog;
import io.dropwizard.hibernate.AbstractDAO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;

import java.util.List;

public class PlayerLogDao extends AbstractDAO<PlayerLog> {
    public PlayerLogDao(SessionFactory factory) {
        super(factory);
    }

    public void save(PlayerLog log) {
        currentSession().save(log);
    }

    public List<Object> find(String targetPlayerId) {
        StringBuilder query = new StringBuilder("from PlayerLog");
        boolean first = true;

        if (!StringUtils.isEmpty(targetPlayerId)) {
            if (first) {
                query.append(" where");
                first = false;
            } else {
                query.append(" and");
            }
            query.append(" playerId = '");
            query.append(targetPlayerId);
            query.append("'");
        }

        query.append(" order by logDateTime desc");

        return currentSession()
                .createQuery(query.toString())
                .setMaxResults(20)
                .list();
    }
}
