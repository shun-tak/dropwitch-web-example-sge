package com.github.shuntak.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuntak.api.ResponseCommonBody;
import com.github.shuntak.entity.*;
import com.github.shuntak.entity.dao.*;
import io.dropwizard.hibernate.UnitOfWork;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Path("/")
@Produces({MediaType.APPLICATION_JSON, "application/x-msgpack"})
@Timed
public class RootResource {
    private ItemDao itemDao;
    private ItemLogDao itemLogDao;
    private MapDao mapDao;
    private PlayerDao playerDao;
    private PlayerLogDao playerLogDao;

    @Context
    UriInfo uriInfo;

    public RootResource(ItemDao itemDao, ItemLogDao itemLogDao, MapDao mapDao, PlayerDao playerDao, PlayerLogDao playerLogDao) {
        this.itemDao = itemDao;
        this.itemLogDao = itemLogDao;
        this.mapDao = mapDao;
        this.playerDao = playerDao;
        this.playerLogDao = playerLogDao;
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
        createPlayerLog(targetPlayerid);

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

    @GET
    @Path("updateItem")
    @UnitOfWork
    public ResponseCommonBody updateItem(
            @QueryParam("targetItemId") String targetItemId,
            @QueryParam("newItemValue") Integer newItemValue
    ) {
        createItemLog(targetItemId);

        Item item = (Item) itemDao.find(targetItemId).get(0);
        if (newItemValue != null) {
            item.setItemValue(newItemValue);
        }

        itemDao.update(item);

        List<Object> data = new ArrayList<>();
        data.add(item);
        return new ResponseCommonBody(data);
    }

    @GET
    @Path("findItemOwner")
    @UnitOfWork
    public ResponseCommonBody findItemOwner(@QueryParam("targetItemId") String targetItemId) {
        List<Object> players;
        List<Object> maps = mapDao.findByItemId(targetItemId);

        if (!maps.isEmpty()) {
            return new ResponseCommonBody(maps);
        }

        players = playerDao.findByItemId(targetItemId);
        return new ResponseCommonBody(players);
    }

    @GET
    @Path("switchItemOwner")
    @UnitOfWork
    public ResponseCommonBody switchItemOwner(
            @QueryParam("targetItemId") String targetItemId,
            @QueryParam("newItemOwner") String newItemOwner
    ) {
        List<Object> data = new ArrayList<>();

        if (StringUtils.startsWith(newItemOwner, "U")) {
            List<Object> players = playerDao.find(newItemOwner);
            if (players.isEmpty()) {
                return new ResponseCommonBody(data);
            }
            createPlayerLog(newItemOwner);
            createItemLog(targetItemId);

            Player player = (Player) players.get(0);
            player.setPlayerItemsString(player.getPlayerItemsString() + "," + targetItemId);
            playerDao.update(player);

            data.add(player);
            return new ResponseCommonBody(data);
        }

        if (StringUtils.startsWith(newItemOwner, "M")) {
            List<Object> maps = mapDao.find(newItemOwner);
            if (maps.isEmpty()) {
                return new ResponseCommonBody(data);
            }
            createItemLog(targetItemId);

            Map map = (Map) maps.get(0);
            map.setMapItemsString(map.getMapItemsString() + "," + targetItemId);
            mapDao.update(map);

            data.add(map);
            return new ResponseCommonBody(data);
        }

        return new ResponseCommonBody(data);
    }

    @GET
    @Path("getPlayerLog")
    @UnitOfWork
    public ResponseCommonBody getPlayerLog(@QueryParam("targetPlayerId") String targetPlayerId) {
        List<Object> logs = playerLogDao.find(targetPlayerId);
        return new ResponseCommonBody(logs);
    }

    @GET
    @Path("getItemLog")
    @UnitOfWork
    public ResponseCommonBody getItemLog(@QueryParam("targetItemId") String targetItemId) {
        List<Object> logs = itemLogDao.find(targetItemId);
        return new ResponseCommonBody(logs);
    }

    @GET
    @Path("movePlayer")
    @UnitOfWork
    public ResponseCommonBody movePlayer(
            @QueryParam("targetPlayerId") String targetPlayerId,
            @QueryParam("newPlayerMap") String newPlayerMap
    ) {
        List<Object> players = playerDao.find(targetPlayerId);
        Player player = (Player) players.get(0);

        // 現在地と目的地が同じなら更新
        if (newPlayerMap.equals(player.getPlayerMap())) {
            createPlayerLog(targetPlayerId);
            player.setPlayerMap(newPlayerMap);
            playerDao.update(player);
            return new ResponseCommonBody(players);
        }

        Map currentMap = (Map) mapDao.find(player.getPlayerMap()).get(0);

        for (String nextMap : currentMap.getMapNext()) {
            if (nextMap.equals(newPlayerMap)) {
                createPlayerLog(targetPlayerId);
                player.setPlayerMap(newPlayerMap);
                playerDao.update(player);
                return new ResponseCommonBody(players);
            }
        }

        // なかったらresult: false
        return new ResponseCommonBody();
    }

    @GET
    @Path("exploreMap")
    @UnitOfWork
    public ResponseCommonBody exploreMap(
            @QueryParam("targetPlayerId") String targetPlayerId
    ) {
        List<Object> players = playerDao.find(targetPlayerId);
        Player player = (Player) players.get(0);
        List<Object> maps = mapDao.find(player.getPlayerMap());
        if (maps.isEmpty()) {
            return new ResponseCommonBody();
        }
        Map map = (Map) maps.get(0);

        // itemがあるかチェック
        List<String> items = map.getMapItems();
        if (items.isEmpty()) {
            return new ResponseCommonBody();
        }

        // item取得
        String getItem = items.remove(0);
        createPlayerLog(targetPlayerId);
        createItemLog(getItem);

        // mapのitem更新
        String itemString = "";
        for (String item : map.getMapItems()) {
            if (itemString.equals("")) {
                itemString += item;
            } else {
                itemString += "," + item;
            }
        }
        map.setMapItemsString(itemString);
        mapDao.update(map);

        player.setPlayerItemsString(player.getPlayerItemsString() + "," + getItem);
        playerDao.update(player);

        Item item = (Item) itemDao.find(getItem).get(0);
        item.setItemValue(Math.max(0, item.getItemValue() - 10));

        return new ResponseCommonBody(players);
    }

    @GET
    @Path("hideItem")
    @UnitOfWork
    public ResponseCommonBody hideItem(
            @QueryParam("targetPlayerId") String targetPlayerId
    ) {
        List<Object> players = playerDao.find(targetPlayerId);
        Player player = (Player) players.get(0);
        List<Object> maps = mapDao.find(player.getPlayerMap());
        if (maps.isEmpty()) {
            return new ResponseCommonBody();
        }
        Map map = (Map) maps.get(0);

        // itemがあるかチェック
        List<String> items = player.getPlayerItems();
        if (items.isEmpty()) {
            return new ResponseCommonBody();
        }

        // item取得
        String getItem = items.remove(0);
        createPlayerLog(targetPlayerId);
        createItemLog(getItem);

        // playerのitem更新
        String itemString = "";
        for (String item : player.getPlayerItems()) {
            if (itemString.equals("")) {
                itemString += item;
            } else {
                itemString += "," + item;
            }
        }
        player.setPlayerItemsString(itemString);
        playerDao.update(player);

        map.setMapItemsString(map.getMapItemsString() + "," + getItem);
        mapDao.update(map);

        Item item = (Item) itemDao.find(getItem).get(0);
        item.setItemValue(Math.min(65535, item.getItemValue() + 10));

        return new ResponseCommonBody(players);
    }

    @GET
    @Path("updatePlayerHp")
    @UnitOfWork
    public ResponseCommonBody updatePlayerHp(
            @QueryParam("targetPlayerId") String targetPlayerId,
            @QueryParam("calcValue") Integer calcValue
    ) {
        List<Object> players = playerDao.find(targetPlayerId);
        Player player = (Player) players.get(0);

        player.setPlayerHp(Math.max(0, Math.min(255, player.getPlayerHp() + calcValue)));
        playerDao.update(player);

        return new ResponseCommonBody(players);
    }

    @GET
    @Path("updatePlayerMp")
    @UnitOfWork
    public ResponseCommonBody updatePlayerMp(
            @QueryParam("targetPlayerId") String targetPlayerId,
            @QueryParam("calcValue") Integer calcValue
    ) {
        List<Object> players = playerDao.find(targetPlayerId);
        Player player = (Player) players.get(0);

        player.setPlayerMp(Math.max(0, Math.min(255, player.getPlayerMp() + calcValue)));
        playerDao.update(player);

        return new ResponseCommonBody(players);
    }

    @GET
    @Path("updatePlayerExp")
    @UnitOfWork
    public ResponseCommonBody updatePlayerExp(
            @QueryParam("targetPlayerId") String targetPlayerId,
            @QueryParam("calcValue") Integer calcValue
    ) {
        List<Object> players = playerDao.find(targetPlayerId);
        Player player = (Player) players.get(0);

        player.setPlayerExp(Math.max(0, Math.min(65535, player.getPlayerExp() + calcValue)));
        playerDao.update(player);

        return new ResponseCommonBody(players);
    }

    @GET
    @Path("updatePlayerAtk")
    @UnitOfWork
    public ResponseCommonBody updatePlayerAtk(
            @QueryParam("targetPlayerId") String targetPlayerId,
            @QueryParam("calcValue") Integer calcValue
    ) {
        List<Object> players = playerDao.find(targetPlayerId);
        Player player = (Player) players.get(0);

        player.setPlayerAtk(Math.max(0, Math.min(255, player.getPlayerAtk() + calcValue)));
        playerDao.update(player);

        return new ResponseCommonBody(players);
    }

    @GET
    @Path("updatePlayerDef")
    @UnitOfWork
    public ResponseCommonBody updatePlayerDef(
            @QueryParam("targetPlayerId") String targetPlayerId,
            @QueryParam("calcValue") Integer calcValue
    ) {
        List<Object> players = playerDao.find(targetPlayerId);
        Player player = (Player) players.get(0);

        player.setPlayerDef(Math.max(0, Math.min(255, player.getPlayerDef() + calcValue)));
        playerDao.update(player);

        return new ResponseCommonBody(players);
    }

    @GET
    @Path("updatePlayerInt")
    @UnitOfWork
    public ResponseCommonBody updatePlayerInt(
            @QueryParam("targetPlayerId") String targetPlayerId,
            @QueryParam("calcValue") Integer calcValue
    ) {
        List<Object> players = playerDao.find(targetPlayerId);
        Player player = (Player) players.get(0);

        player.setPlayerInt(Math.max(0, Math.min(255, player.getPlayerInt() + calcValue)));
        playerDao.update(player);

        return new ResponseCommonBody(players);
    }

    @GET
    @Path("updatePlayerAgi")
    @UnitOfWork
    public ResponseCommonBody updatePlayerAgi(
            @QueryParam("targetPlayerId") String targetPlayerId,
            @QueryParam("calcValue") Integer calcValue
    ) {
        List<Object> players = playerDao.find(targetPlayerId);
        Player player = (Player) players.get(0);

        player.setPlayerAgi(Math.max(0, Math.min(255, player.getPlayerAgi() + calcValue)));
        playerDao.update(player);

        return new ResponseCommonBody(players);
    }

    @GET
    @Path("rankPlayerHp")
    @UnitOfWork
    public ResponseCommonBody rankPlayerHp(
            @QueryParam("isAscend") boolean isAscend
    ) {
        List<Object> players = playerDao.orderByHp(isAscend);
        return new ResponseCommonBody(players);
    }

    @GET
    @Path("rankPlayerMp")
    @UnitOfWork
    public ResponseCommonBody rankPlayerMp(
            @QueryParam("isAscend") boolean isAscend
    ) {
        List<Object> players = playerDao.orderByMp(isAscend);
        return new ResponseCommonBody(players);
    }

    @GET
    @Path("rankPlayerExp")
    @UnitOfWork
    public ResponseCommonBody rankPlayerExp(
            @QueryParam("isAscend") boolean isAscend
    ) {
        List<Object> players = playerDao.orderByExp(isAscend);
        return new ResponseCommonBody(players);
    }

    @GET
    @Path("rankPlayerAtk")
    @UnitOfWork
    public ResponseCommonBody rankPlayerAtk(
            @QueryParam("isAscend") boolean isAscend
    ) {
        List<Object> players = playerDao.orderByAtk(isAscend);
        return new ResponseCommonBody(players);
    }

    @GET
    @Path("rankPlayerDef")
    @UnitOfWork
    public ResponseCommonBody rankPlayerDef(
            @QueryParam("isAscend") boolean isAscend
    ) {
        List<Object> players = playerDao.orderByDef(isAscend);
        return new ResponseCommonBody(players);
    }

    @GET
    @Path("rankPlayerInt")
    @UnitOfWork
    public ResponseCommonBody rankPlayerInt(
            @QueryParam("isAscend") boolean isAscend
    ) {
        List<Object> players = playerDao.orderByInt(isAscend);
        return new ResponseCommonBody(players);
    }

    @GET
    @Path("rankPlayerAgi")
    @UnitOfWork
    public ResponseCommonBody rankPlayerAgi(
            @QueryParam("isAscend") boolean isAscend
    ) {
        List<Object> players = playerDao.orderByAgi(isAscend);
        return new ResponseCommonBody(players);
    }

    @GET
    @Path("rankItemValue")
    @UnitOfWork
    public ResponseCommonBody rankItemValue(
            @QueryParam("isAscend") boolean isAscend
    ) {
        List<Object> items = itemDao.orderByValue(isAscend);
        return new ResponseCommonBody(items);
    }

    private void createPlayerLog(String targetPlayerid) {
        PlayerLog log = new PlayerLog();
        log.setPlayerId(targetPlayerid);
        log.setLogDateTime(DateTime.now());
        log.setApiPath(uriInfo.getPath());
        log.setApiParam(uriInfo.getRequestUri().getQuery());
        playerLogDao.save(log);
    }

    private void createItemLog(String targetItemId) {
        ItemLog log = new ItemLog();
        log.setItemId(targetItemId);
        log.setLogDateTime(DateTime.now());
        log.setApiPath(uriInfo.getPath());
        log.setApiParam(uriInfo.getRequestUri().getQuery());
        itemLogDao.save(log);
    }
}
