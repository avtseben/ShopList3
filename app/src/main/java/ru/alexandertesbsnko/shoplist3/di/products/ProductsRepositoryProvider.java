package ru.alexandertesbsnko.shoplist3.di.products;

import ru.alexandertesbsnko.shoplist3.di.router.RouterProvider;
import ru.alexandertesbsnko.shoplist3.repository.products.IProductsRepository;
import ru.alexandertesbsnko.shoplist3.repository.products.ProductsRepository;

/**
 * Created by avtseben on 26.10.17.
 */

public class ProductsRepositoryProvider {

    public static ProductsRepositoryProvider INSTANCE = new ProductsRepositoryProvider();

    private ProductsRepositoryProvider(){}

    public IProductsRepository provide(){
        return new ProductsRepository();
    }
}
