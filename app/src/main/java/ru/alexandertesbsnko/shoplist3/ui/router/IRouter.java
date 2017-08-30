package ru.alexandertesbsnko.shoplist3.ui.router;

import android.util.Log;

/**
 * Created by avtseben on 29.08.17.
 */

public interface IRouter {
    void navigate(String screen);
    void backTo(String screen);
    void finishChain();

    class Screen {
        public static final String TOP_LIST = "top_list";
        public static final String SHOPING_LIST = "shoping_list";
    }

    class Fake implements IRouter{
        @Override
        public void navigate(String screen) {
            System.out.println("Navigate to: " + screen);
            Log.d(this.getClass().getSimpleName(),"Navigate to: " + screen);
        }

        @Override
        public void backTo(String screen) {
            System.out.println("Back to: " + screen);
        }

        @Override
        public void finishChain() {
            System.out.println("Finish");
        }
    }
}
