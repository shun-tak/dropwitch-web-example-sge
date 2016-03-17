package com.github.shuntak.resources;

import com.codahale.metrics.annotation.Timed;
import com.github.shuntak.api.ResponseCommonBody;
import com.github.shuntak.entity.dao.ItemDao;
import com.github.shuntak.entity.dao.PostDao;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/searchItem")
@Produces({MediaType.APPLICATION_JSON, "application/x-msgpack"})
@Timed
public class SearchItemResource {
    private ItemDao itemDao;
    private PostDao postDao;

    public SearchItemResource(ItemDao itemDao, PostDao postDao) {
        this.itemDao = itemDao;
        this.postDao = postDao;
    }

    @GET
    @UnitOfWork
    public ResponseCommonBody get() {
        return new ResponseCommonBody(new ArrayList<>());
    }
}
