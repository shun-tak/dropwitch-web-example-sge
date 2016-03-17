package com.github.shuntak.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/searchUser")
@Produces({MediaType.APPLICATION_JSON, "application/x-msgpack"})
@Timed
public class SearchUserResource {
}
