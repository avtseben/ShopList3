package ru.alexandertesbsnko.shoplist3.data_source.net.model.request.products;

import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.AbstractRequest;

public class AtFindProductRequest extends AbstractRequest {
    private Long id;
    private String name;

    private String nameLike;

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

}
