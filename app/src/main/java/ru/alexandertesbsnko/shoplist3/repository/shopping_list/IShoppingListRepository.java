package ru.alexandertesbsnko.shoplist3.repository.shopping_list;


import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;

/**
 * Created by avtseben on 25.10.17.
 */

public interface IShoppingListRepository {
    ShoppingList loadShoppingListById(long id) throws Exception;

}
