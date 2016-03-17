package com.github.shuntak.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuntak.api.ResponseCommonBody;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/")
@Produces({MediaType.APPLICATION_JSON, "application/x-msgpack"})
@Timed
public class RootResource {

    public RootResource() {
    }

    @GET
    @Path("getInfo")
    @UnitOfWork
    public ResponseCommonBody getInfo() {
        List<Object> data = new ArrayList<>();
        data.add(new Object() {
            @JsonProperty
            String information = "本日のお知らせ";
        });
        return new ResponseCommonBody(data);
    }
}
