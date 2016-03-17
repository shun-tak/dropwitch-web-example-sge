package com.github.shuntak.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player {
    private String playerId;

    @Id
    @javax.persistence.Column(name = "playerId", nullable = true, length = 34)
    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    private String playerName;

    @Basic
    @javax.persistence.Column(name = "playerName", nullable = true, length = -1)
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    private Integer playerHp;

    @Basic
    @javax.persistence.Column(name = "playerHp", nullable = true)
    public Integer getPlayerHp() {
        return playerHp;
    }

    public void setPlayerHp(Integer playerHp) {
        this.playerHp = playerHp;
    }

    private Integer playerMp;

    @Basic
    @javax.persistence.Column(name = "playerMp", nullable = true)
    public Integer getPlayerMp() {
        return playerMp;
    }

    public void setPlayerMp(Integer playerMp) {
        this.playerMp = playerMp;
    }

    private Integer playerExp;

    @Basic
    @javax.persistence.Column(name = "playerExp", nullable = true)
    public Integer getPlayerExp() {
        return playerExp;
    }

    public void setPlayerExp(Integer playerExp) {
        this.playerExp = playerExp;
    }

    private Integer playerAtk;

    @Basic
    @javax.persistence.Column(name = "playerAtk", nullable = true)
    public Integer getPlayerAtk() {
        return playerAtk;
    }

    public void setPlayerAtk(Integer playerAtk) {
        this.playerAtk = playerAtk;
    }

    private Integer playerDef;

    @Basic
    @javax.persistence.Column(name = "playerDef", nullable = true)
    public Integer getPlayerDef() {
        return playerDef;
    }

    public void setPlayerDef(Integer playerDef) {
        this.playerDef = playerDef;
    }

    private Integer playerInt;

    @Basic
    @javax.persistence.Column(name = "playerInt", nullable = true)
    public Integer getPlayerInt() {
        return playerInt;
    }

    public void setPlayerInt(Integer playerInt) {
        this.playerInt = playerInt;
    }

    private Integer playerAgi;

    @Basic
    @javax.persistence.Column(name = "playerAgi", nullable = true)
    public Integer getPlayerAgi() {
        return playerAgi;
    }

    public void setPlayerAgi(Integer playerAgi) {
        this.playerAgi = playerAgi;
    }

    private String playerItems;

    @Basic
    @javax.persistence.Column(name = "playerItems", nullable = true, length = -1)
    public String getPlayerItems() {
        return playerItems;
    }

    public void setPlayerItems(String playerItems) {
        this.playerItems = playerItems;
    }

    private String playerMap;

    @Basic
    @javax.persistence.Column(name = "playerMap", nullable = true, length = 34)
    public String getPlayerMap() {
        return playerMap;
    }

    public void setPlayerMap(String playerMap) {
        this.playerMap = playerMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (playerId != null ? !playerId.equals(player.playerId) : player.playerId != null) return false;
        if (playerName != null ? !playerName.equals(player.playerName) : player.playerName != null) return false;
        if (playerHp != null ? !playerHp.equals(player.playerHp) : player.playerHp != null) return false;
        if (playerMp != null ? !playerMp.equals(player.playerMp) : player.playerMp != null) return false;
        if (playerExp != null ? !playerExp.equals(player.playerExp) : player.playerExp != null) return false;
        if (playerAtk != null ? !playerAtk.equals(player.playerAtk) : player.playerAtk != null) return false;
        if (playerDef != null ? !playerDef.equals(player.playerDef) : player.playerDef != null) return false;
        if (playerInt != null ? !playerInt.equals(player.playerInt) : player.playerInt != null) return false;
        if (playerAgi != null ? !playerAgi.equals(player.playerAgi) : player.playerAgi != null) return false;
        if (playerItems != null ? !playerItems.equals(player.playerItems) : player.playerItems != null) return false;
        if (playerMap != null ? !playerMap.equals(player.playerMap) : player.playerMap != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playerId != null ? playerId.hashCode() : 0;
        result = 31 * result + (playerName != null ? playerName.hashCode() : 0);
        result = 31 * result + (playerHp != null ? playerHp.hashCode() : 0);
        result = 31 * result + (playerMp != null ? playerMp.hashCode() : 0);
        result = 31 * result + (playerExp != null ? playerExp.hashCode() : 0);
        result = 31 * result + (playerAtk != null ? playerAtk.hashCode() : 0);
        result = 31 * result + (playerDef != null ? playerDef.hashCode() : 0);
        result = 31 * result + (playerInt != null ? playerInt.hashCode() : 0);
        result = 31 * result + (playerAgi != null ? playerAgi.hashCode() : 0);
        result = 31 * result + (playerItems != null ? playerItems.hashCode() : 0);
        result = 31 * result + (playerMap != null ? playerMap.hashCode() : 0);
        return result;
    }
}
