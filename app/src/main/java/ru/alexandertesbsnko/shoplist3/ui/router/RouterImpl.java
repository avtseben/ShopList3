package ru.alexandertesbsnko.shoplist3.ui.router;

/**
 * Created by avtseben on 29.08.17.
 */

public class RouterImpl implements IRouter{
    @Override
    public void navigate(String screen) {
        System.out.println(screen);

    }

    @Override
    public void backTo(String screen) {
        System.out.println("backTo" + screen);

    }

    @Override
    public void finishChain() {
        System.out.println("finish");
    }
}
