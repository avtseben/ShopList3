package ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter;

import ru.alexandertesbsnko.shoplist3.ui.shoping_list.view.IShoppingListView;

/**
 * Created by avtseben on 25.10.17.
 */

public abstract class AbstractPresenter {

    protected IShoppingListView view;


    public void bindView(IShoppingListView view) {
        this.view = view;
    }

    public void unbindView() {
        view = null;
    }
}
