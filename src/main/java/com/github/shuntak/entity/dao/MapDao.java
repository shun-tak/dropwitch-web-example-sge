package com.github.shuntak.entity.dao;

import com.github.shuntak.entity.Map;
import io.dropwizard.hibernate.AbstractDAO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;

import java.util.List;

public class MapDao extends AbstractDAO<Map> {
    public MapDao(SessionFactory factory) {
        super(factory);
    }

    public List<Object> find(String targetMapId) {
        StringBuilder query = new StringBuilder("from Map");
        boolean first = true;

        if (!StringUtils.isEmpty(targetMapId)) {
            if (first) {
                query.append(" where");
                first = false;
            } else {
                query.append(" and");
            }
            query.append(" mapId = '");
            query.append(targetMapId);
            query.append("'");
        }

        return currentSession()
                .createQuery(query.toString())
                .list();
    }
}
