package ru.alexandertesbsnko.shoplist3.di.router;


import dagger.Subcomponent;
import ru.alexandertesbsnko.shoplist3.di.top_list.TopListModule;
import ru.alexandertesbsnko.shoplist3.di.top_list.TopListScope;
import ru.alexandertesbsnko.shoplist3.ui.top_list.presenter.ITopPresenter;
import ru.alexandertesbsnko.shoplist3.ui.top_list.view.TopListFragment;


@Subcomponent(modules = {RouterModule.class})
@TopListScope
public interface RouterComponent {
    void inject(ITopPresenter presenter);
}