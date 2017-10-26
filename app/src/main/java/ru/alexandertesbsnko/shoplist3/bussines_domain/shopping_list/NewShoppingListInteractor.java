package ru.alexandertesbsnko.shoplist3.bussines_domain.shopping_list;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.di.products.ProductsRepositoryProvider;
import ru.alexandertesbsnko.shoplist3.repository.products.IProductsRepository;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Product;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import rx.Observable;
import rx.functions.Func1;

public class NewShoppingListInteractor implements IShoppingListInteractor{

    IProductsRepository productsRepository = ProductsRepositoryProvider.INSTANCE.provide();

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
}
