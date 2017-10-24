package ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists;

import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.AbstractResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShoppingListDTO;
import java.util.List;

public class AtFindShoppingListsResponse extends AbstractResponse{

    private List<AtShoppingListDTO> shoppingLists;

    public List<AtShoppingListDTO> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(List<AtShoppingListDTO> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }
}
