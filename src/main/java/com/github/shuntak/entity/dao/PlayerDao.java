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

    // TODO orderBy*の共通化
    public List<Object> orderByHp(boolean isAscend) {
        StringBuilder query = new StringBuilder("from Player");

        query.append(" order by playerHp");
        if (isAscend) {
            query.append(" asc");
        } else {
            query.append(" desc");
        }

        return currentSession()
                .createQuery(query.toString())
                .setMaxResults(20)
                .list();
    }

    public List<Object> orderByMp(boolean isAscend) {
        StringBuilder query = new StringBuilder("from Player");

        query.append(" order by playerMp");
        if (isAscend) {
            query.append(" asc");
        } else {
            query.append(" desc");
        }

        return currentSession()
                .createQuery(query.toString())
                .setMaxResults(20)
                .list();
    }

    public List<Object> orderByExp(boolean isAscend) {
        StringBuilder query = new StringBuilder("from Player");

        query.append(" order by playerExp");
        if (isAscend) {
            query.append(" asc");
        } else {
            query.append(" desc");
        }

        return currentSession()
                .createQuery(query.toString())
                .setMaxResults(20)
                .list();
    }

    public List<Object> orderByAtk(boolean isAscend) {
        StringBuilder query = new StringBuilder("from Player");

        query.append(" order by playerAtk");
        if (isAscend) {
            query.append(" asc");
        } else {
            query.append(" desc");
        }

        return currentSession()
                .createQuery(query.toString())
                .setMaxResults(20)
                .list();
    }

    public List<Object> orderByDef(boolean isAscend) {
        StringBuilder query = new StringBuilder("from Player");

        query.append(" order by playerDef");
        if (isAscend) {
            query.append(" asc");
        } else {
            query.append(" desc");
        }

        return currentSession()
                .createQuery(query.toString())
                .setMaxResults(20)
                .list();
    }

    public List<Object> orderByInt(boolean isAscend) {
        StringBuilder query = new StringBuilder("from Player");

        query.append(" order by playerInt");
        if (isAscend) {
            query.append(" asc");
        } else {
            query.append(" desc");
        }

        return currentSession()
                .createQuery(query.toString())
                .setMaxResults(20)
                .list();
    }

    public List<Object> orderByAgi(boolean isAscend) {
        StringBuilder query = new StringBuilder("from Player");

        query.append(" order by playerAgi");
        if (isAscend) {
            query.append(" asc");
        } else {
            query.append(" desc");
        }

        return currentSession()
                .createQuery(query.toString())
                .setMaxResults(20)
                .list();
    }
}
