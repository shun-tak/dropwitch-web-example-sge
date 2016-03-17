package com.github.shuntak.entity.dao;

import com.github.shuntak.entity.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDao extends AbstractDAO<User> {
    public UserDao(SessionFactory factory) {
        super(factory);
    }

    public List<Object> find(
            String userId,
            Integer userPublicScoreGTE,
            Integer userPublicScoreLTE,
            Integer userFriendsNumberGTE,
            Integer userFriendsNumberLTE,
            String userFriendsIncludeUserIds,
            String userFriendsNotIncludeUserIds,
            String postId,
            Integer postDateTimeGTE,
            Integer postDateTimeLTE,
            String postItemId,
            Integer maxPostItemScoreGTE,
            Integer minPostItemScoreLTE,
            String postItemState,
            String postItemStateNotEQ,
            String postLikeUsersIncludeUserIds,
            String postLikeUsersNotIncludeUserIds,
            Integer limit
    ) {
        StringBuilder query = new StringBuilder("from User");
        boolean first = true;

        if (!StringUtils.isEmpty(userId)) {
            if (first) {
                query.append(" where");
                first = false;
            } else {
                query.append(" and");
            }
            query.append(" userId = '");
            query.append(userId);
            query.append("'");
        }

        query.append(" order by userNo asc");

        return currentSession()
                .createQuery(query.toString())
                .setMaxResults(limit)
                .list();
    }
}
