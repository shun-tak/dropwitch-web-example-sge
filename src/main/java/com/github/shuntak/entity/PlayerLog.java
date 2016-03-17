package com.github.shuntak.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
public class PlayerLog {
    private String playerId;
    private String apiPath;
    private String apiParam;
    @JsonIgnore
    private DateTime logDateTime;
    @JsonProperty("logDateTime")
    private String logDateTimeString;

    @Id
    @Column(name = "playerId", nullable = true, length = 34)
    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    @Basic
    @Column(name = "apiPath", nullable = true, length = 255)
    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    @Basic
    @Column(name = "apiParam", nullable = true, length = -1)
    public String getApiParam() {
        return apiParam;
    }

    public void setApiParam(String apiParam) {
        this.apiParam = apiParam;
    }

    @Basic
    @Column(name = "logDateTime", nullable = true)
    public DateTime getLogDateTime() {
        return logDateTime;
    }

    public void setLogDateTime(DateTime logDateTime) {
        this.logDateTime = logDateTime;
        this.logDateTimeString = logDateTime.toString("YYYY-MM-DD-hh:mm:ss");
    }

    @Transient
    public String getLogDateTimeString() {
        return logDateTimeString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerLog playerLog = (PlayerLog) o;

        if (playerId != null ? !playerId.equals(playerLog.playerId) : playerLog.playerId != null) return false;
        if (apiPath != null ? !apiPath.equals(playerLog.apiPath) : playerLog.apiPath != null) return false;
        if (apiParam != null ? !apiParam.equals(playerLog.apiParam) : playerLog.apiParam != null) return false;
        if (logDateTime != null ? !logDateTime.equals(playerLog.logDateTime) : playerLog.logDateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playerId != null ? playerId.hashCode() : 0;
        result = 31 * result + (apiPath != null ? apiPath.hashCode() : 0);
        result = 31 * result + (apiParam != null ? apiParam.hashCode() : 0);
        result = 31 * result + (logDateTime != null ? logDateTime.hashCode() : 0);
        return result;
    }
}
