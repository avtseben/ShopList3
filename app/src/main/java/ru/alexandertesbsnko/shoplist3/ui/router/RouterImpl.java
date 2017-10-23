package ru.alexandertesbsnko.shoplist3.ui.router;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.view.ShoppingListFragment;
import ru.alexandertesbsnko.shoplist3.ui.top_list.view.TopListFragment;

public class RouterImpl implements IRouter{

    private FragmentManager fragmentManager;

    public void setFragmentManager(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void navigate(String screen) {
        this.navigate(screen,new Bundle());
    }

    @Override
    public void navigate(String screen, Bundle bundle) {
        if(screen.equals(Screen.TOP_LIST)){
            abstractNavigate(new TopListFragment());
        } else
        if(screen.equals(Screen.SHOPING_LIST)){
            abstractNavigate(ShoppingListFragment.newInstance(bundle));
        }
    }

    private void abstractNavigate(Fragment fragment){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
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
}
