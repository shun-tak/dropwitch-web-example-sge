package com.github.shuntak.entity.dao;

import com.github.shuntak.entity.Map;
import io.dropwizard.hibernate.AbstractDAO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapDao extends AbstractDAO<Map> {
    public MapDao(SessionFactory factory) {
        super(factory);
    }

    public List<Object> findAll() {
        return currentSession()
                .createQuery("from Map")
                .list();
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

    public void update(Map map) {
        currentSession().update(map);
    }

    public List<Object> findByItemId(String targetItemId) {
        List<Object> maps = findAll();
        for (Object map : maps) {
            List<String> mapItems = ((Map) map).getMapItems();
            for (String item : mapItems) {
                if (item.equals(targetItemId)) {
                    List<Object> ret = new ArrayList<>();
                    ret.add(map);
                    return ret;
                }
            }
        }
        return new ArrayList<>();
    }
}
