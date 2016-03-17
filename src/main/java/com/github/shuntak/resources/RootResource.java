package com.github.shuntak.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuntak.api.ResponseCommonBody;
import com.github.shuntak.entity.dao.ItemDao;
import com.github.shuntak.entity.dao.MapDao;
import com.github.shuntak.entity.dao.PlayerDao;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/")
@Produces({MediaType.APPLICATION_JSON, "application/x-msgpack"})
@Timed
public class RootResource {
    private ItemDao itemDao;
    private MapDao mapDao;
    private PlayerDao playerDao;

    public RootResource(ItemDao itemDao, MapDao mapDao, PlayerDao playerDao) {
        this.itemDao = itemDao;
        this.mapDao = mapDao;
        this.playerDao = playerDao;
    }

    @GET
    @Path("getInfo")
    public ResponseCommonBody getInfo() {
        List<Object> data = new ArrayList<>();
        data.add(new Object() {
            @JsonProperty
            String information = "本日のお知らせ";
        });
        return new ResponseCommonBody(data);
    }

    @GET
    @Path("readPlayer")
    @UnitOfWork
    public ResponseCommonBody readPlayer(@QueryParam("targetPlayerId") String targetPlayerid) {
        List<Object> players = playerDao.find(targetPlayerid);

        return new ResponseCommonBody(players);
    }

}
