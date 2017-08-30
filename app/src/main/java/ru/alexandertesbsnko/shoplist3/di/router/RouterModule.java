package ru.alexandertesbsnko.shoplist3.di.router;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.alexandertesbsnko.shoplist3.ui.router.IRouter;

@Module
public class RouterModule {

    @Provides
    @Singleton
    IRouter provideRouter() {
        return new IRouter.Fake();
    }

}