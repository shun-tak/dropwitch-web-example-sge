package com.github.shuntak.entity;

import javax.persistence.*;

@Entity
@Table(name="item")
public class Item {
    private String itemId;
    private String itemName;
    private String itemType;
    private Integer itemValue;
    private String itemEffectTarget;
    private Integer itemEffectValue;

    @Id
    @Column(name = "itemId", nullable = true, length = 34)
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "itemName", nullable = true, length = -1)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Basic
    @Column(name = "itemType", nullable = true, length = -1)
    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @Basic
    @Column(name = "itemValue", nullable = true)
    public Integer getItemValue() {
        return itemValue;
    }

    public void setItemValue(Integer itemValue) {
        this.itemValue = itemValue;
    }

    @Basic
    @Column(name = "itemEffectTarget", nullable = true, length = 255)
    public String getItemEffectTarget() {
        return itemEffectTarget;
    }

    public void setItemEffectTarget(String itemEffectTarget) {
        this.itemEffectTarget = itemEffectTarget;
    }

    @Basic
    @Column(name = "itemEffectValue", nullable = true)
    public Integer getItemEffectValue() {
        return itemEffectValue;
    }

    public void setItemEffectValue(Integer itemEffectValue) {
        this.itemEffectValue = itemEffectValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (itemId != null ? !itemId.equals(item.itemId) : item.itemId != null) return false;
        if (itemName != null ? !itemName.equals(item.itemName) : item.itemName != null) return false;
        if (itemType != null ? !itemType.equals(item.itemType) : item.itemType != null) return false;
        if (itemValue != null ? !itemValue.equals(item.itemValue) : item.itemValue != null) return false;
        if (itemEffectTarget != null ? !itemEffectTarget.equals(item.itemEffectTarget) : item.itemEffectTarget != null)
            return false;
        if (itemEffectValue != null ? !itemEffectValue.equals(item.itemEffectValue) : item.itemEffectValue != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId != null ? itemId.hashCode() : 0;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (itemType != null ? itemType.hashCode() : 0);
        result = 31 * result + (itemValue != null ? itemValue.hashCode() : 0);
        result = 31 * result + (itemEffectTarget != null ? itemEffectTarget.hashCode() : 0);
        result = 31 * result + (itemEffectValue != null ? itemEffectValue.hashCode() : 0);
        return result;
    }
}
