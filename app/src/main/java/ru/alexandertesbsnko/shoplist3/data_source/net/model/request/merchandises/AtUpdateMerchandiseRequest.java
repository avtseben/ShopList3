package ru.alexandertesbsnko.shoplist3.data_source.net.model.request.merchandises;

import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.AbstractRequest;

public class AtUpdateMerchandiseRequest extends AbstractRequest {
    private Long id;
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
