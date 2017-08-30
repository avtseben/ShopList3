package ru.alexandertesbsnko.shoplist3.ui.router;

/**
 * Created by avtseben on 29.08.17.
 */

public interface IRouter {
    void navigate(String screen);
    void backTo(String screen);
    void finishChain(String screen);

    class Screen {
        public static final String TOP_LIST = "top_list";
    }
}
