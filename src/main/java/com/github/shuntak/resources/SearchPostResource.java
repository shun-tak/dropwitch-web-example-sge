package com.github.shuntak.resources;

import com.codahale.metrics.annotation.Timed;
import com.github.shuntak.api.ResponseCommonBody;
import com.github.shuntak.entity.dao.ItemDao;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/searchPost")
@Produces({MediaType.APPLICATION_JSON, "application/x-msgpack"})
@Timed
public class SearchPostResource {
    private ItemDao itemDao;

    public SearchPostResource(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @GET
    @UnitOfWork
    public ResponseCommonBody get() {
        return new ResponseCommonBody(new ArrayList<>());
    }
}
