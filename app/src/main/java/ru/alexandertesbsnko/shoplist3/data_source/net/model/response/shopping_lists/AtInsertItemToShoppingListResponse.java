package ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists;


import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShoppingItemDTO;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.AbstractResponse;

public class AtInsertItemToShoppingListResponse extends AbstractResponse {

    private AtShoppingItemDTO newShoppingItem;

    public AtShoppingItemDTO getNewShoppingItem() {
        return newShoppingItem;
    }

    public void setNewShoppingItem(AtShoppingItemDTO newShoppingItem) {
        this.newShoppingItem = newShoppingItem;
    }
}
