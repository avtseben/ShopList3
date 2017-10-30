package ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists;


import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.AbstractRequest;

public class AtCreateShoppingListRequest extends AbstractRequest {

    private String name;

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
