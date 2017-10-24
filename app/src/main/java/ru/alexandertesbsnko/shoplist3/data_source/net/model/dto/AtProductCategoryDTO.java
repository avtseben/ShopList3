package ru.alexandertesbsnko.shoplist3.data_source.net.model.dto;


import ru.alexandertsebenko.shoplist.api.ifaces.Gid;
import ru.rtcomm.platform.api.annotation.UniqField;

public class AtProductCategoryDTO implements Gid {
    public Long id;

    @UniqField
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

    public void setName(String briefName) {
        this.name = briefName;
    }
}
