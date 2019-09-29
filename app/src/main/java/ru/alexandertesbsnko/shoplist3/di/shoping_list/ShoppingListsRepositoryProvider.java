package ru.alexandertesbsnko.shoplist3.di.shoping_list;

import ru.alexandertesbsnko.shoplist3.repository.shopping_list.AsyncRestShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.repository.shopping_list.IShoppingListRepository;


public class ShoppingListsRepositoryProvider {

    public static ShoppingListsRepositoryProvider INSTANCE = new ShoppingListsRepositoryProvider();

    private ShoppingListsRepositoryProvider(){}

    public IShoppingListRepository provide(){
        return new AsyncRestShoppingListRepository();
    }
}
