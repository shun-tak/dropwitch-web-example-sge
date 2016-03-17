package com.github.shuntak.entity.dao;

import com.github.shuntak.entity.Player;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class PlayerDao extends AbstractDAO<Player> {
    public PlayerDao(SessionFactory factory) {
        super(factory);
    }
}
