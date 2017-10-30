package ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists;

import ru.alexandertesbsnko.shoplist3.data_source.common.AckResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShoppingListDTO;


public class AtCreateShoppingListResponse extends AckResponse {

    private AtShoppingListDTO newShoppingList;

    public AtShoppingListDTO getNewShoppingList() {
        return newShoppingList;
    }

    public void setNewShoppingList(AtShoppingListDTO newShoppingList) {
        this.newShoppingList = newShoppingList;
    }
}
