package ru.alexandertesbsnko.shoplist3.di.shoping_list;


import dagger.Module;
import dagger.Provides;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter.IShoppingListPresenter;
import ru.alexandertesbsnko.shoplist3.ui.top_list.presenter.ITopPresenter;

@Module
public class ShopingListModule {

    @Provides
    @ShopingListScope
    IShoppingListPresenter providePresenter() {
        return new IShoppingListPresenter.Fake();//Inject
    }
}