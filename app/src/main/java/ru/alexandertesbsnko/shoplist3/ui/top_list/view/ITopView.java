package ru.alexandertesbsnko.shoplist3.ui.top_list.view;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import ru.alexandertesbsnko.shoplist3.ui.top_list.model.TopListItem;

/**
 * Created by avtseben on 29.08.17.
 */

public interface ITopView {

    void setUpTopList(List<TopListItem> topList);

    void shopErrorMessage(String s);

    interface OnShopListItemClickListener {
        void onItemClicked(TopListItem shopListObj);
    }

    interface OnNewListButtonClickListener {
        void onNewListClicked();
    }

    void removeItemFromList(int position);

}
