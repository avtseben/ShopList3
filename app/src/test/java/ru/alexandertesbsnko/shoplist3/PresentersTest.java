package ru.alexandertesbsnko.shoplist3;

import org.junit.Test;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Product;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter.ShoppingListPresenter;

/**
 * Created by avtseben on 01.11.17.
 */

public class PresentersTest {

    @Test
    public void testPresenterProductSearch() {
        List<Product> finded = new ShoppingListPresenter().searchProductsByNameSync("Мол");
        System.out.println();
    }
}
