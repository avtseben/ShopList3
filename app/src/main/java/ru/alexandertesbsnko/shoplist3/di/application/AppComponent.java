package ru.alexandertesbsnko.shoplist3.di.application;

import javax.inject.Singleton;

import dagger.Component;
import ru.alexandertesbsnko.shoplist3.di.shoping_list.ShopingListComponent;
import ru.alexandertesbsnko.shoplist3.di.shoping_list.ShopingListModule;
import ru.alexandertesbsnko.shoplist3.di.top_list.TopListComponent;
import ru.alexandertesbsnko.shoplist3.di.top_list.TopListModule;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    TopListComponent plus(TopListModule topListModule);
    ShopingListComponent plus(ShopingListModule topListModule);

//    ProfileComponent plus(ProfileModule profileModule);
//
//    TransferComponent plus(TransferModule transferModule);

}
