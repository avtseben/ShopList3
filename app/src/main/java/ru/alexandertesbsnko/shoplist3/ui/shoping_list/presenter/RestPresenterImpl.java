package ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.repository.shopping_list.AsyncRestShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.repository.shopping_list.IShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.repository.shopping_list.RestShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.view.IShoppingListView;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by avtseben on 24.10.17.
 */
//TODO тест класс
public class RestPresenterImpl implements IShoppingListPresenter {

    private IShoppingListView view;
    IShoppingListRepository repository = new RestShoppingListRepository();
    private ShoppingList shoppingList;

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

    @Override
    public Observable<ShoppingList> asyncLoadShoppingListById(long id) {
        Observable<ShoppingList> observable = new AsyncRestShoppingListRepository().loadShoppingListById(id);
        observable.subscribe(new Subscriber<ShoppingList>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ShoppingList shoppingList) {
                setShoppingList(shoppingList);
                System.out.println(">>Complete from presenter" + shoppingList.getName());
            }
        });
        return observable;
    }

    void setShoppingList(ShoppingList shoppingList){
        this.shoppingList = shoppingList;
        System.out.println(">> from presenter" + this.shoppingList.getName());
    }

    @Override
    public void bindView(IShoppingListView view) {
        this.view = view;
    }


    @Override
    public void unbindView() {
        view = null;
    }
}
