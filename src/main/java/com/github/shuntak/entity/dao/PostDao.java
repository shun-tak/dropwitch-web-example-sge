package com.github.shuntak.entity.dao;

import com.github.shuntak.entity.Post;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class PostDao extends AbstractDAO<Post> {
    public PostDao(SessionFactory factory) {
        super(factory);
    }
}
