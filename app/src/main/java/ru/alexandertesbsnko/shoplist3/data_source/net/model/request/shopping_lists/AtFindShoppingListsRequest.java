package ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists;

import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.AbstractRequest;

public class AtFindShoppingListsRequest extends AbstractRequest {
    private Long id;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
