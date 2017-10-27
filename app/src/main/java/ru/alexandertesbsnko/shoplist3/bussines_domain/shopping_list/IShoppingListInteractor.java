package ru.alexandertesbsnko.shoplist3.bussines_domain.shopping_list;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.data_source.common.AckResponse;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Product;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import rx.Observable;

public interface IShoppingListInteractor {
    Observable<ShoppingList> loadShoppingListById(long id);
    Observable<List<Product>> searchProductsByName(String pattern);
    Observable<ShoppingItem> insertItemToShoppingList(long shoppingListId, long productId);
    Observable<AckResponse> buyShoppingItem(long id);
    Observable<AckResponse> deleteShoppingItem(long id);
}
