package ru.alexandertesbsnko.shoplist3.di.application;

import javax.inject.Singleton;

import dagger.Component;
import ru.alexandertesbsnko.shoplist3.di.router.RouterComponent;
import ru.alexandertesbsnko.shoplist3.di.router.RouterModule;
import ru.alexandertesbsnko.shoplist3.di.top_list.TopListComponent;
import ru.alexandertesbsnko.shoplist3.di.top_list.TopListModule;

@Component(modules = {AppModule.class, RouterModule.class})
@Singleton
public interface AppComponent {

    TopListComponent plus(TopListModule topListModule);
//    RouterComponent plus(RouterModule routerModule);

//    ProfileComponent plus(ProfileModule profileModule);
//
//    TransferComponent plus(TransferModule transferModule);

}
