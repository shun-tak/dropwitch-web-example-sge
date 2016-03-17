package com.github.shuntak.resources;

import com.codahale.metrics.annotation.Timed;
import com.github.shuntak.api.ResponseMasterBody;
import com.github.shuntak.api.data.MasterResponseData;
import com.github.shuntak.entity.dao.MasterCommonDao;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/master")
@Produces({MediaType.APPLICATION_JSON, "application/x-msgpack"})
@Timed
public class MasterResource {
    private MasterCommonDao masterCommonDao;
    private Optional<MasterResponseData> data;

    public MasterResource(MasterCommonDao masterCommonDao) {
        this.masterCommonDao = masterCommonDao;
        this.data = Optional.empty();
    }

    @GET
    @UnitOfWork
    public ResponseMasterBody get() {
        return new ResponseMasterBody(this.data.orElseGet(() -> {
            List common = masterCommonDao.getAll();
            this.data = Optional.of(new MasterResponseData(common));
            return this.data.orElse(null);
        }));
    }

    @DELETE
    @Path("/cache")
    public ResponseMasterBody deleteCache() {
        this.data = Optional.empty();
        return new ResponseMasterBody(null);
    }
}
