package ru.alexandertesbsnko.shoplist3.di.shoping_list;

import ru.alexandertesbsnko.shoplist3.bussines_domain.shopping_list.IShoppingListInteractor;
import ru.alexandertesbsnko.shoplist3.bussines_domain.shopping_list.ShoppingListInteractor;


public class ShoppingListsInteractorProvider {

    public static ShoppingListsInteractorProvider INSTANCE = new ShoppingListsInteractorProvider();

    private ShoppingListsInteractorProvider(){}

    public IShoppingListInteractor provide(){
        return new ShoppingListInteractor();
    }
}
