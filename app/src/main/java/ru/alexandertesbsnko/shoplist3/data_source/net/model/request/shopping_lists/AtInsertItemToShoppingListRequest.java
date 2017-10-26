package ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists;


import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.AbstractRequest;

/**
 * Created by avtseben on 26.10.17.
 */
public class AtInsertItemToShoppingListRequest extends AbstractRequest {

    private Long productId;
    private Long shoppingListId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(Long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }
}
