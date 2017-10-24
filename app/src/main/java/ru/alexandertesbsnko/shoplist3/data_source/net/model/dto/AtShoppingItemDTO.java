package ru.alexandertesbsnko.shoplist3.data_source.net.model.dto;

/**
 * Created by avtseben on 24.10.17.
 */
public class AtShoppingItemDTO {

    private Long id;
    private Integer state;
    private Double quantity;
    private AtMerchandiseDTO merchandise;
    private AtShopDTO shop;
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public AtMerchandiseDTO getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(AtMerchandiseDTO merchandise) {
        this.merchandise = merchandise;
    }

    public AtShopDTO getShop() {
        return shop;
    }

    public void setShop(AtShopDTO shop) {
        this.shop = shop;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
