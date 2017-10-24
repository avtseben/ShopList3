package ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shops;

import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.AbstractRequest;


public class AtFindShopRequest extends AbstractRequest {
    private Long id;
    private String name;

    private String nameLike;

    private String address;

    private String addressLike;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressLike() {
        return addressLike;
    }

    public void setAddressLike(String addressLike) {
        this.addressLike = addressLike;
    }
}
