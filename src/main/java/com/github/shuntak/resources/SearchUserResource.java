package com.github.shuntak.resources;

import com.codahale.metrics.annotation.Timed;
import com.github.shuntak.api.ResponseCommonBody;
import com.github.shuntak.entity.dao.PostDao;
import com.github.shuntak.entity.dao.UserDao;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/searchUser")
@Produces({MediaType.APPLICATION_JSON, "application/x-msgpack"})
@Timed
public class SearchUserResource {
    private PostDao postDao;
    private UserDao userDao;

    public SearchUserResource(PostDao postDao, UserDao userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GET
    @UnitOfWork
    public ResponseCommonBody get(
            @QueryParam("findByUserId") String userId,
            @QueryParam("findByUserPublicScoreGTE") Integer userPublicScoreGTE,
            @QueryParam("findByUserPublicScoreLTE") Integer userPublicScoreLTE,
            @QueryParam("findByUserFriendsNumberGTE") Integer userFriendsNumberGTE,
            @QueryParam("findByUserFriendsNumberLTE") Integer userFriendsNumberLTE,
            @QueryParam("findByUserFriendsIncludeUserIds") String userFriendsIncludeUserIds,
            @QueryParam("findByUserFriendsNotIncludeUserIds") String userFriendsNotIncludeUserIds,
            @QueryParam("findByPostId") String postId,
            @QueryParam("findByPostDateTimeGTE") Integer postDateTimeGTE,
            @QueryParam("findByPostDateTimeLTE") Integer postDateTimeLTE,
            @QueryParam("findByPostItemId") String postItemId,
            @QueryParam("findByMaxPostItemScoreGTE") Integer maxPostItemScoreGTE,
            @QueryParam("findByMinPostItemScoreLTE") Integer minPostItemScoreLTE,
            @QueryParam("findByPostItemState") String postItemState,
            @QueryParam("findByPostItemStateNotEQ") String postItemStateNotEQ,
            @QueryParam("findByPostLikeUsersIncludeUserIds") String postLikeUsersIncludeUserIds,
            @QueryParam("findByPostLikeUsersNotIncludeUserIds") String postLikeUsersNotIncludeUserIds,
            @QueryParam("limit") @DefaultValue("100") Integer limit
    ) {
        List<Object> users = userDao.find(userId, userPublicScoreGTE, userPublicScoreLTE, userFriendsNumberGTE, userFriendsNumberLTE, userFriendsIncludeUserIds, userFriendsNotIncludeUserIds, postId, postDateTimeGTE, postDateTimeLTE, postItemId, maxPostItemScoreGTE, minPostItemScoreLTE,
                postItemState, postItemStateNotEQ, postLikeUsersIncludeUserIds, postLikeUsersNotIncludeUserIds, limit);

        return new ResponseCommonBody(users);
    }
}
