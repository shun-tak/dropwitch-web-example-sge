package com.github.shuntak.entity.dao;

import com.github.shuntak.entity.Player;
import io.dropwizard.hibernate.AbstractDAO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class PlayerDao extends AbstractDAO<Player> {
    public PlayerDao(SessionFactory factory) {
        super(factory);
    }

    public List<Object> findAll() {
        return currentSession()
                .createQuery("from Player")
                .list();
    }

    public List<Object> find(String targetPlayerId) {
        StringBuilder query = new StringBuilder("from Player");
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

        return currentSession()
                .createQuery(query.toString())
                .list();
    }

    public void update(Player player) {
        currentSession().update(player);
    }

    public List<Object> findByItemId(String targetItemId) {
        List<Object> players = findAll();
        for (Object player : players) {
            List<String> playerItems = ((Player) player).getPlayerItems();
            for (String item : playerItems) {
                if (item.equals(targetItemId)) {
                    List<Object> ret = new ArrayList<>();
                    ret.add(player);
                    return ret;
                }
            }
        }
        return new ArrayList<>();
    }
}
