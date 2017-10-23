package ru.alexandertesbsnko.shoplist3.ui;

import android.support.v4.app.Fragment;

import ru.alexandertesbsnko.shoplist3.ui.router.IRouter;

public abstract class AbstractFragment extends Fragment {
    protected IRouter router;

    public void bindRouter(IRouter router) {
        this.router = router;
    }
}
