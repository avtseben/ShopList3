package ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.repository.shopping_list.IShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.repository.shopping_list.RestShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.view.IShoppingListView;

/**
 * Created by avtseben on 24.10.17.
 */
//TODO тест класс
public class RestPresenterImpl extends AbstractPresenter implements IShoppingListPresenter {


    IShoppingListRepository repository = new RestShoppingListRepository();

    @Override
    public ShoppingList loadShoppingListById(long id){
        ShoppingList shoppingList = null;
        try {
            shoppingList = repository.loadShoppingListById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shoppingList;
    }

    @Override
    public ShoppingList loadNewShoppingList() {
        return null;
    }

    @Override
    public void buyShoppingItem(long id) {

    }

    @Override
    public void deleteShoppingItem(long id) {

    }

    @Override
    public List<ShoppingItem> findShoppingItemLike(String pattern) {
        return null;
    }

    @Override
    public void addShoppingItem(ShoppingItem shoppingItem, long id) {

    }
}
