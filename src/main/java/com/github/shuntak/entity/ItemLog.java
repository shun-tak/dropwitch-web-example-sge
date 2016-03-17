package com.github.shuntak.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class ItemLog {
    private String itemId;
    private String apiPath;
    private String apiParam;
    private LocalDateTime logDateTime;

    @Id
    @Column(name = "itemId", nullable = true, length = 34)
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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
    public LocalDateTime getLogDateTime() {
        return logDateTime;
    }

    public void setLogDateTime(LocalDateTime logDateTime) {
        this.logDateTime = logDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemLog itemLog = (ItemLog) o;

        if (itemId != null ? !itemId.equals(itemLog.itemId) : itemLog.itemId != null) return false;
        if (apiPath != null ? !apiPath.equals(itemLog.apiPath) : itemLog.apiPath != null) return false;
        if (apiParam != null ? !apiParam.equals(itemLog.apiParam) : itemLog.apiParam != null) return false;
        if (logDateTime != null ? !logDateTime.equals(itemLog.logDateTime) : itemLog.logDateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId != null ? itemId.hashCode() : 0;
        result = 31 * result + (apiPath != null ? apiPath.hashCode() : 0);
        result = 31 * result + (apiParam != null ? apiParam.hashCode() : 0);
        result = 31 * result + (logDateTime != null ? logDateTime.hashCode() : 0);
        return result;
    }
}
