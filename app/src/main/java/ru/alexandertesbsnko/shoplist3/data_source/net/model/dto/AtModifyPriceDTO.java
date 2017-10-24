package ru.alexandertesbsnko.shoplist3.data_source.net.model.dto;


import ru.alexandertsebenko.shoplist.api.ifaces.Gid;
import ru.rtcomm.platform.api.annotation.UniqField;

public class AtModifyPriceDTO implements Gid {
    public Long id;

    @UniqField
    public Long productId;
    @UniqField
    public Long packId;
    @UniqField
    public Long brandId;
    @UniqField
    public Long shopId;
    public Integer price;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPackId() {
        return packId;
    }

    public void setPackId(Long packId) {
        this.packId = packId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
