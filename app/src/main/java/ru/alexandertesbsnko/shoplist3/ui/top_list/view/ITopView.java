package ru.alexandertesbsnko.shoplist3.ui.top_list.view;

import ru.alexandertesbsnko.shoplist3.ui.top_list.model.TopListItemDataModel;

/**
 * Created by avtseben on 29.08.17.
 */

public interface ITopView {
    interface OnShopListItemClickListener {
        void onItemClicked(TopListItemDataModel shopListObj);
    }

    interface OnNewListButtonClickListener {
        void onNewListClicked();
    }
}
