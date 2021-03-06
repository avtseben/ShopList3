package ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter;


import java.util.List;

import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Product;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.view.IShoppingListView;

public interface IShoppingListPresenter {
    void loadShoppingList(long id);
    void incrementQuantity(long shoppingItemId);
    void decrementQuantity(long shoppingItemId);
    void bindView(IShoppingListView view);
    void unbindView();
    List<Product> searchProductsByNameSync(String pattern);
    void addProduct(Product product);
    void buyShoppingItem(long id);
    void deleteShoppingItem(long id);
    void updatePrice(ShoppingItem shoppingItem, double newPrice);
}
