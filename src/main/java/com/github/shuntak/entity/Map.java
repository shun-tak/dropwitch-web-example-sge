package com.github.shuntak.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name="map")
public class Map {
    private String mapId;

    @Id
    @javax.persistence.Column(name = "mapId", nullable = true, length = 34)
    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    private String mapName;

    @Basic
    @javax.persistence.Column(name = "mapName", nullable = true, length = -1)
    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    private String mapType;

    @Basic
    @javax.persistence.Column(name = "mapType", nullable = true, length = -1)
    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    @JsonIgnore
    private String mapNextString;

    @JsonProperty("mapNext")
    private List<String> mapNext;

    @Basic
    @javax.persistence.Column(name = "mapNext", nullable = true, length = -1)
    public String getMapNextString() {
        return mapNextString;
    }

    public void setMapNextString(String mapNext) {
        setMapNext(mapNext);
    }

    @Transient
    public List<String> getMapNext() {
        return this.mapNext;
    }

    public void setMapNext(String mapNext) {
        this.mapNextString = mapNext;
        this.mapNext = Stream.of(StringUtils.split(mapNext, ",")).collect(Collectors.toList());
    }

    @JsonIgnore
    private String mapItemsString;

    @JsonProperty("mapItems")
    private List<String> mapItems;

    @Basic
    @javax.persistence.Column(name = "mapItems", nullable = true, length = -1)
    public String getMapItemsString() {
        return mapItemsString;
    }

    public void setMapItemsString(String mapItems) {
        setMapItems(mapItems);
    }

    @Transient
    public List<String> getMapItems() {
        return this.mapItems;
    }

    public void setMapItems(String mapItems) {
        this.mapItemsString = mapItems;
        this.mapItems = Stream.of(StringUtils.split(mapItems, ",")).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Map map = (Map) o;

        if (mapId != null ? !mapId.equals(map.mapId) : map.mapId != null) return false;
        if (mapName != null ? !mapName.equals(map.mapName) : map.mapName != null) return false;
        if (mapType != null ? !mapType.equals(map.mapType) : map.mapType != null) return false;
        if (mapNextString != null ? !mapNextString.equals(map.mapNextString) : map.mapNextString != null) return false;
        if (mapItemsString != null ? !mapItemsString.equals(map.mapItemsString) : map.mapItemsString != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mapId != null ? mapId.hashCode() : 0;
        result = 31 * result + (mapName != null ? mapName.hashCode() : 0);
        result = 31 * result + (mapType != null ? mapType.hashCode() : 0);
        result = 31 * result + (mapNextString != null ? mapNextString.hashCode() : 0);
        result = 31 * result + (mapItemsString != null ? mapItemsString.hashCode() : 0);
        return result;
    }
}
