package ru.alexandertesbsnko.shoplist3;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import ru.alexandertesbsnko.shoplist3.di.application.AppComponent;
import ru.alexandertesbsnko.shoplist3.di.application.AppModule;
import ru.alexandertesbsnko.shoplist3.di.application.DaggerAppComponent;

/**
 * Shop List Application
 */

public class SLApplication extends Application{

    // dagger2 appComponent
    @SuppressWarnings("NullableProblems")
    @NonNull
    private AppComponent appComponent;

    private static Context context;

    @NonNull
    public static SLApplication get(@NonNull Context context) {
        return (SLApplication) context.getApplicationContext();
    }

    @NonNull
    public static Context getContext() {
        return context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = prepareAppComponent().build();
        SLApplication.context = getApplicationContext();
    }

    @NonNull
    private DaggerAppComponent.Builder prepareAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this));
    }

    @NonNull
    public AppComponent applicationComponent() {
        return appComponent;
    }

}
