package ru.alexandertesbsnko.shoplist3.di.router;

import ru.alexandertesbsnko.shoplist3.ui.router.IRouter;
import ru.alexandertesbsnko.shoplist3.ui.router.RouterImpl;

/**
 * Created by avtseben on 22.10.17.
 */

public class RouterProvider {
    public static RouterProvider INSTANCE = new RouterProvider();

    private IRouter router = new RouterImpl();

    private RouterProvider(){}

    public  IRouter getRouter(){
        return router;
    }
}
