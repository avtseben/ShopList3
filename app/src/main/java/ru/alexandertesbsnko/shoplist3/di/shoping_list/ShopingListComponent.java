package ru.alexandertesbsnko.shoplist3.di.shoping_list;


import dagger.Subcomponent;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.view.ShoppingListFragment;


@Subcomponent(modules = {ShopingListModule.class})
@ShopingListScope
public interface ShopingListComponent {

    void inject(ShoppingListFragment shopingListFragment);

}