package com.github.shuntak.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {
    private String itemId;
    private Long itemNo;
    private String itemSupplier;
    private Integer itemSoldQuantity;
    private Integer itemSalePrice;
    private String itemTags;
    private String itemImage;

    @Id
    @Column(name = "itemId")
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "itemNo")
    public Long getItemNo() {
        return itemNo;
    }

    public void setItemNo(Long itemNo) {
        this.itemNo = itemNo;
    }

    @Basic
    @Column(name = "itemSupplier")
    public String getItemSupplier() {
        return itemSupplier;
    }

    public void setItemSupplier(String itemSupplier) {
        this.itemSupplier = itemSupplier;
    }

    @Basic
    @Column(name = "itemSoldQuantity")
    public Integer getItemSoldQuantity() {
        return itemSoldQuantity;
    }

    public void setItemSoldQuantity(Integer itemSoldQuantity) {
        this.itemSoldQuantity = itemSoldQuantity;
    }

    @Basic
    @Column(name = "itemSalePrice")
    public Integer getItemSalePrice() {
        return itemSalePrice;
    }

    public void setItemSalePrice(Integer itemSalePrice) {
        this.itemSalePrice = itemSalePrice;
    }

    @Basic
    @Column(name = "itemTags")
    public String getItemTags() {
        return itemTags;
    }

    public void setItemTags(String itemTags) {
        this.itemTags = itemTags;
    }

    @Basic
    @Column(name = "itemImage")
    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (itemId != null ? !itemId.equals(item.itemId) : item.itemId != null) return false;
        if (itemNo != null ? !itemNo.equals(item.itemNo) : item.itemNo != null) return false;
        if (itemSupplier != null ? !itemSupplier.equals(item.itemSupplier) : item.itemSupplier != null) return false;
        if (itemSoldQuantity != null ? !itemSoldQuantity.equals(item.itemSoldQuantity) : item.itemSoldQuantity != null)
            return false;
        if (itemSalePrice != null ? !itemSalePrice.equals(item.itemSalePrice) : item.itemSalePrice != null)
            return false;
        if (itemTags != null ? !itemTags.equals(item.itemTags) : item.itemTags != null) return false;
        if (itemImage != null ? !itemImage.equals(item.itemImage) : item.itemImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId != null ? itemId.hashCode() : 0;
        result = 31 * result + (itemNo != null ? itemNo.hashCode() : 0);
        result = 31 * result + (itemSupplier != null ? itemSupplier.hashCode() : 0);
        result = 31 * result + (itemSoldQuantity != null ? itemSoldQuantity.hashCode() : 0);
        result = 31 * result + (itemSalePrice != null ? itemSalePrice.hashCode() : 0);
        result = 31 * result + (itemTags != null ? itemTags.hashCode() : 0);
        result = 31 * result + (itemImage != null ? itemImage.hashCode() : 0);
        return result;
    }
}
