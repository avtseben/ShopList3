package ru.alexandertesbsnko.shoplist3.ui.shoping_list.view;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;

/**
 * Created by avtseben on 29.08.17.
 */

public interface IShoppingListView {

    void setUpShopingList(List<ShoppingItem> shoppingItems);

    void setTotalCost(double totalCost);

    void setTotalBoughtCost(double totalBoughtCost);

    void setListName(String name);

    void addShoppingItem(ShoppingItem shoppingItem);

    void searchShoppingItems(String pattern);

    void setFindedShoppingItems(List<ShoppingItem> findedShoppingItems);
    void incrementQuantity(long shoppingItemId);
    void decrementQuantity(long shoppingItemId);

    interface OnSendButtonClickListener {
        void onSendButtonClicked(ShoppingList shoppingList);
    }
}

