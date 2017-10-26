package ru.alexandertesbsnko.shoplist3.repository.shopping_list;

import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import rx.Observable;

public interface IShoppingListRepository {
    Observable<ShoppingList> loadShoppingListById(long id);
    Observable<ShoppingItem> insertItemToShoppingList(long shoppingListId, long productId);
}
