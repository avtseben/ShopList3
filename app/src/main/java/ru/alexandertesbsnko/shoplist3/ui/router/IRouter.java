package ru.alexandertesbsnko.shoplist3.ui.router;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;

/**
 * Created by avtseben on 29.08.17.
 */

public interface IRouter {
    void navigate(String screen, Bundle bundle);
    void backTo(String screen);
    void finishChain();
    void setFragmentManager(FragmentManager fragmentManager);

    class Screen {
        public static final String TOP_LIST = "top_list";
        public static final String SHOPING_LIST = "shoping_list";
    }

    class Fake implements IRouter{
        @Override
        public void navigate(String screen, Bundle bundle) {
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

        @Override
        public void setFragmentManager(FragmentManager fragmentManager){
            //NOP
        }
    }
}
