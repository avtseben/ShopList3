package ru.alexandertesbsnko.shoplist3.bussines_domain.shopping_list;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.di.products.ProductsRepositoryProvider;
import ru.alexandertesbsnko.shoplist3.repository.products.IProductsRepository;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Product;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import rx.Observable;

public class ShoppingListInteractor implements IShoppingListInteractor{

    IProductsRepository productsRepository = ProductsRepositoryProvider.INSTANCE.provide();

    @Override
    public Observable<ShoppingList> loadShoppingListById(long id) {
        return null;
    }

    @Override
    public Observable<List<Product>> searchProductsByName(String pattern) {
        return productsRepository.searchProductsByNameLike("%" + pattern + "%");
    }
}
