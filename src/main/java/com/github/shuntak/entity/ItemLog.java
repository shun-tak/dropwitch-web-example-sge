package com.github.shuntak.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.HashMap;

@Entity
public class ItemLog {
    private String itemId;
    private String apiPath;
    @JsonIgnore
    private String apiParam;
    @JsonProperty("apiParam")
    private HashMap<String, String> apiParamMap = new HashMap<>();
    @JsonIgnore
    private DateTime logDateTime;
    @JsonProperty("logDateTime")
    private String logDateTimeString;

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
        for (String param : StringUtils.split(apiParam, "&")) {
            String[] keyVal = StringUtils.split(param, "=");
            apiParamMap.put(keyVal[0], keyVal[1]);
        }
    }

    @Transient
    public HashMap<String, String> getApiParamMap() {
        return apiParamMap;
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
