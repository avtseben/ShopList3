package ru.alexandertesbsnko.shoplist3.data_source.net.model.dto;


import ru.alexandertsebenko.shoplist.api.ifaces.Gid;
import ru.rtcomm.platform.api.annotation.UniqField;

public class AtShopDTO implements Gid {
    public Long id;

    @UniqField
    public String address;
    public String name;

    @Override
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
