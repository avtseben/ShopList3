package ru.alexandertesbsnko.shoplist3.bussines_domain.shopping_list;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Product;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import rx.Observable;

public interface IShoppingListInteractor {
    Observable<ShoppingList> loadShoppingListById(long id);
    Observable<List<Product>> searchProductsByName(String pattern);

}
