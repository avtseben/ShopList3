package ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists;

import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.AbstractRequest;

public class AtDeleteShoppingListRequest extends AbstractRequest {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}




