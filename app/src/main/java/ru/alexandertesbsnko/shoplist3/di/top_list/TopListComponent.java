package ru.alexandertesbsnko.shoplist3.di.top_list;


import dagger.Subcomponent;
import ru.alexandertesbsnko.shoplist3.ui.top_list.view.TopListFragment;


@Subcomponent(modules = {TopListModule.class})
@TopListScope
public interface TopListComponent {

    void inject(TopListFragment mainFragment);

}