package com.github.shuntak.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuntak.api.ResponseCommonBody;
import com.github.shuntak.entity.Item;
import com.github.shuntak.entity.Map;
import com.github.shuntak.entity.Player;
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

    @GET
    @Path("updatePlayer")
    @UnitOfWork
    public ResponseCommonBody updatePlayer(
            @QueryParam("targetPlayerId") String targetPlayerid,
            @QueryParam("newPlayerHp") Integer newPlayerHp,
            @QueryParam("newPlayerMp") Integer newPlayerMp,
            @QueryParam("newPlayerExp") Integer newPlayerExp,
            @QueryParam("newPlayerAtk") Integer newPlayerAtk,
            @QueryParam("newPlayerDef") Integer newPlayerDef,
            @QueryParam("newPlayerInt") Integer newPlayerInt,
            @QueryParam("newPlayerAgi") Integer newPlayerAgi,
            @QueryParam("newPlayerItems") String newPlayerItems,
            @QueryParam("newPlayerMap") String newPlayerMap
    ) {
        Player player = (Player) playerDao.find(targetPlayerid).get(0);
        if (newPlayerHp != null) {
            player.setPlayerHp(newPlayerHp);
        }
        if (newPlayerMp != null) {
            player.setPlayerMp(newPlayerMp);
        }
        if (newPlayerExp != null) {
            player.setPlayerExp(newPlayerExp);
        }
        if (newPlayerAtk != null) {
            player.setPlayerAtk(newPlayerAtk);
        }
        if (newPlayerDef != null) {
            player.setPlayerDef(newPlayerDef);
        }
        if (newPlayerInt != null) {
            player.setPlayerInt(newPlayerInt);
        }
        if (newPlayerAgi != null) {
            player.setPlayerAgi(newPlayerAgi);
        }
        if (newPlayerItems != null) {
            player.setPlayerItemsString(newPlayerItems);
        }
        if (newPlayerMap != null) {
            player.setPlayerMap(newPlayerMap);
        }

        playerDao.update(player);

        List<Object> data = new ArrayList<>();
        data.add(player);
        return new ResponseCommonBody(data);
    }

    @GET
    @Path("readMap")
    @UnitOfWork
    public ResponseCommonBody readMap(@QueryParam("targetMapId") String targetMapId) {
        List<Object> maps = mapDao.find(targetMapId);

        return new ResponseCommonBody(maps);
    }

    @GET
    @Path("updateMap")
    @UnitOfWork
    public ResponseCommonBody updateMap(
            @QueryParam("targetMapId") String targetMapId,
            @QueryParam("newMapItems") String newMapItems
    ) {
        Map map = (Map) mapDao.find(targetMapId).get(0);
        if (newMapItems != null) {
            map.setMapItemsString(newMapItems);
        }

        mapDao.update(map);

        List<Object> data = new ArrayList<>();
        data.add(map);
        return new ResponseCommonBody(data);
    }

    @GET
    @Path("readItem")
    @UnitOfWork
    public ResponseCommonBody readItem(@QueryParam("targetItemId") String targetItemId) {
        List<Object> data = itemDao.find(targetItemId);
        return new ResponseCommonBody(data);
    }
}
