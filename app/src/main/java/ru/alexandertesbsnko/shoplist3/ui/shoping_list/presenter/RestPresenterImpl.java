package ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.view.IShoppingListView;

/**
 * Created by avtseben on 24.10.17.
 */

public class RestPresenterImpl implements IShoppingListPresenter {

    @Override
    public ShoppingList loadShoppingListById(long id) {
        return null;
    }

    @Override
    public ShoppingList loadNewShoppingList() {
        return null;
    }

    @Override
    public void bindView(IShoppingListView view) {

    }

    @Override
    public void unbindView() {

    }

    @Override
    public void buyShoppingItem(long id) {

    }

    @Override
    public void deleteShoppingItem(long id) {

    }

    @Override
    public List<ShoppingItem> findShoppingItemLike(String pattern) {
        return null;
    }

    @Override
    public void addShoppingItem(ShoppingItem shoppingItem, long id) {

    }
}
