package ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShoppingItemDTO;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.AbstractRequest;

/**
 * Created by avtseben on 27.10.17.
 */

public class AtUpdateShoppingItemsRequest extends AbstractRequest {
    private List<AtShoppingItemDTO> shoppingItemList;

    public List<AtShoppingItemDTO> getShoppingItemList() {
        return shoppingItemList;
    }

    public void setShoppingItemList(List<AtShoppingItemDTO> shoppingItemList) {
        this.shoppingItemList = shoppingItemList;
    }
}
