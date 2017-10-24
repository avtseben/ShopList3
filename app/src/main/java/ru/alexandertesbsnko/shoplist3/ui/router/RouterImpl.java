package ru.alexandertesbsnko.shoplist3.ui.router;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.ui.AbstractFragment;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.view.ShoppingListFragment;
import ru.alexandertesbsnko.shoplist3.ui.top_list.model.TopListItem;
import ru.alexandertesbsnko.shoplist3.ui.top_list.view.TopListFragment;

public class RouterImpl implements IRouter {

    private FragmentManager fragmentManager;

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void navigate(String screen) {
        this.navigate(screen, new Bundle());
    }

    @Override
    public void navigate(String screen, Bundle bundle) {
        if (screen.equals(Screen.TOP_LIST)) {
            abstractNavigate(new TopListFragment());
        } else if (screen.equals(Screen.SHOPING_LIST)) {
            abstractNavigate(ShoppingListFragment.newInstance(bundle));
        }
    }

    private void abstractNavigate(AbstractFragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        fragment.bindRouter(this);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void backTo(String screen) {
        System.out.println("backTo" + screen);

    }

    @Override
    public void finishChain() {
        System.out.println("finish");
    }

    /**
     * В фрагменте списка нажали кнопку отправить список
     * переходим к фрагменту формирования списка людей кому отправлять
     * список
     */
    @Override
    public void onSendButtonClicked(ShoppingList shopListPojo) {
        System.out.println("Send List Button Clicked");
    }

    @Override
    public void onNewListClicked() {
        this.navigate(IRouter.Screen.SHOPING_LIST);
    }

    @Override
    public void onItemClicked(TopListItem shopListObj) {
        Bundle bundle = new Bundle();
        bundle.putLong(ShoppingListFragment.SHOP_LIST_ID,shopListObj.getId());
        this.navigate(IRouter.Screen.SHOPING_LIST,bundle);
    }
}
