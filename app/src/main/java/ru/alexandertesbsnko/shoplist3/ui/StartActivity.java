package ru.alexandertesbsnko.shoplist3.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.di.router.RouterProvider;
import ru.alexandertesbsnko.shoplist3.ui.router.RouterImpl;
import ru.alexandertesbsnko.shoplist3.ui.top_list.view.TopListFragment;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        FragmentManager fm = getSupportFragmentManager();
        if(savedInstanceState == null){
            FragmentTransaction ft = fm.beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.fragment_container, new TopListFragment());
            ft.commit();
        }
        RouterProvider.INSTANCE.getRouter().setFragmentManager(fm);
    }
}
