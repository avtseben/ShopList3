package ru.alexandertesbsnko.shoplist3.bussines_domain.shopping_list;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.di.products.ProductsRepositoryProvider;
import ru.alexandertesbsnko.shoplist3.di.shoping_list.ShoppingListsRepositoryProvider;
import ru.alexandertesbsnko.shoplist3.repository.products.IProductsRepository;
import ru.alexandertesbsnko.shoplist3.repository.shopping_list.IShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Product;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import rx.Observable;
import rx.functions.Func1;

public class ShoppingListInteractor implements IShoppingListInteractor{

    IProductsRepository productsRepository = ProductsRepositoryProvider.INSTANCE.provide();
    IShoppingListRepository shoppingListRepository = ShoppingListsRepositoryProvider.INSTANCE.provide();

    @Override
    public Observable<ShoppingList> loadShoppingListById(long id) {
        return null;
    }

    @Override
    public Observable<List<Product>> searchProductsByName(String pattern) {
        pattern = pattern.trim().toLowerCase();
        String capitalizedPattern = pattern.substring(0,1).toUpperCase() + pattern.substring(1);
        final Observable<List<Product>> observableCapitalized = productsRepository.searchProductsByNameLike("%" + capitalizedPattern + "%");
        return observableCapitalized;
    }

    @Override
    public Observable<ShoppingItem> insertItemToShoppingList(long shoppingListId, long productId){
        return shoppingListRepository.insertItemToShoppingList(shoppingListId,productId);
    }
}
