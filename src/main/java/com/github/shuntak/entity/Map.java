package com.github.shuntak.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    private String mapNext;

    @Basic
    @javax.persistence.Column(name = "mapNext", nullable = true, length = -1)
    public String getMapNext() {
        return mapNext;
    }

    public void setMapNext(String mapNext) {
        this.mapNext = mapNext;
    }

    private String mapItems;

    @Basic
    @javax.persistence.Column(name = "mapItems", nullable = true, length = -1)
    public String getMapItems() {
        return mapItems;
    }

    public void setMapItems(String mapItems) {
        this.mapItems = mapItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Map map = (Map) o;

        if (mapId != null ? !mapId.equals(map.mapId) : map.mapId != null) return false;
        if (mapName != null ? !mapName.equals(map.mapName) : map.mapName != null) return false;
        if (mapType != null ? !mapType.equals(map.mapType) : map.mapType != null) return false;
        if (mapNext != null ? !mapNext.equals(map.mapNext) : map.mapNext != null) return false;
        if (mapItems != null ? !mapItems.equals(map.mapItems) : map.mapItems != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mapId != null ? mapId.hashCode() : 0;
        result = 31 * result + (mapName != null ? mapName.hashCode() : 0);
        result = 31 * result + (mapType != null ? mapType.hashCode() : 0);
        result = 31 * result + (mapNext != null ? mapNext.hashCode() : 0);
        result = 31 * result + (mapItems != null ? mapItems.hashCode() : 0);
        return result;
    }
}
