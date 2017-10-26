package ru.alexandertesbsnko.shoplist3.repository.products;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Product;
import rx.Observable;


public interface IProductsRepository {
    Observable<List<Product>> searchProductsByNameLike(String pattern);
}
