package ru.alexandertesbsnko.shoplist3.ui.shoping_list.view;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Product;
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

    //Autocomplete searching
    void searchShoppingItems(String pattern); //#Uplink interface
    void setFindedProducts(List<Product> findedProducts);
    void addShoppingItem(ShoppingItem shoppingItem);

    //Change shoppingItem quantity #Uplink interface
    void incrementQuantity(long shoppingItemId);
    void decrementQuantity(long shoppingItemId);

    void shopErrorMessage(String message);

    interface OnSendButtonClickListener {
        void onSendButtonClicked(ShoppingList shoppingList);
    }
}

