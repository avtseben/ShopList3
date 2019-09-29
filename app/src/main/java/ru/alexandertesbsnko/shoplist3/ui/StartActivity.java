package ru.alexandertesbsnko.shoplist3.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.di.SandBox;
import ru.alexandertesbsnko.shoplist3.di.router.RouterProvider;
import ru.alexandertesbsnko.shoplist3.ui.router.IRouter;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        IRouter router = RouterProvider.INSTANCE.getRouter();
        router.setFragmentManager(getSupportFragmentManager());
        router.navigate(IRouter.Screen.TOP_LIST);

        SandBox.INSTANCE.set(Context.class, getApplicationContext());
    }
}
