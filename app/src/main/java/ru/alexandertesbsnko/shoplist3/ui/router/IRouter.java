package ru.alexandertesbsnko.shoplist3.ui.router;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import ru.alexandertesbsnko.shoplist3.ui.shoping_list.view.IShoppingListView;
import ru.alexandertesbsnko.shoplist3.ui.top_list.view.ITopView;

/**
 * Created by avtseben on 29.08.17.
 */

public interface IRouter extends
        IShoppingListView.OnSendButtonClickListener
       ,ITopView.OnNewListButtonClickListener
       ,ITopView.OnShopListItemClickListener {

    void navigate(String screen, Bundle bundle);
    void navigate(String screen);
    void backTo(String screen);
    void finishChain();
    void setFragmentManager(FragmentManager fragmentManager);

    class Screen {
        public static final String TOP_LIST = "top_list";
        public static final String SHOPING_LIST = "shoping_list";
    }
}
