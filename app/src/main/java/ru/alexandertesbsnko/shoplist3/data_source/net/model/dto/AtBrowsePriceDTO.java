package ru.alexandertesbsnko.shoplist3.data_source.net.model.dto;


import ru.alexandertsebenko.shoplist.api.ifaces.Gid;
import ru.rtcomm.platform.api.annotation.UniqField;

public class AtBrowsePriceDTO  {
    public Long priceId;

    @UniqField
    public String productName;
    @UniqField
    public String packName;
    @UniqField
    public String brandName;
    @UniqField
    public String shopName;

    public Integer price;

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
