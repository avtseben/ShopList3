package ru.alexandertesbsnko.shoplist3.di.top_list;


import dagger.Module;
import dagger.Provides;
import ru.alexandertesbsnko.shoplist3.ui.router.IRouter;
import ru.alexandertesbsnko.shoplist3.ui.top_list.presenter.ITopPresenter;
import ru.alexandertesbsnko.shoplist3.ui.top_list.presenter.TopPresenter;

@Module
public class TopListModule {

    @Provides
    @TopListScope
    ITopPresenter provideITopPresenter() {
        return new TopPresenter();//Inject
    }
}