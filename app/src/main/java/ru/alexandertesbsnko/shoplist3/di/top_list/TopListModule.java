package ru.alexandertesbsnko.shoplist3.di.top_list;


import dagger.Module;
import dagger.Provides;
import ru.alexandertesbsnko.shoplist3.ui.top_list.presenter.ITopPresenter;

/**
 * @author e.matsyuk
 */
@Module
public class TopListModule {

    @Provides
    @TopListScope
    ITopPresenter provideITopPresenter() {
        return new ITopPresenter.Fake();//Inject
    }

}