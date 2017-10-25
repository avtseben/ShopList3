package ru.alexandertesbsnko.shoplist3.bussines_domain.shopping_list;

import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;

public interface IShoppingListInteractor {

    ShoppingList loadShoppingListById(long id);

}
