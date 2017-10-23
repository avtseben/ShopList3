package ru.alexandertesbsnko.shoplist3.ui.shoping_list.view;

import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;

/**
 * Created by avtseben on 29.08.17.
 */

public interface IShoppingListView {

    interface OnSendButtonClickListener {
        void onSendButtonClicked(ShoppingList shoppingList);
    }
}

