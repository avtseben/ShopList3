package ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import ru.alexandertesbsnko.shoplist3.repository.shopping_list.AsyncRestShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.repository.shopping_list.IShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.repository.shopping_list.RestShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.view.IShoppingListView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by avtseben on 24.10.17.
 */
//TODO тест класс
public class AnotherRestPresenterImpl implements IShoppingListPresenter {

    private IShoppingListView view;
    IShoppingListRepository repository = new RestShoppingListRepository();
    private ShoppingList shoppingList;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    public void loadShoppingList(){
        loadShoppingListFromData(1);
    }


    private void loadShoppingListFromData(long id) {
        Subscription subscription = new AsyncRestShoppingListRepository().loadShoppingListById(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShoppingList>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        handleErrorLoadShoppingList(e);
                    }

                    @Override
                    public void onNext(ShoppingList shoppingList) {
                        handleSuccessLoadShoppingList(shoppingList);
                    }
                });
        compositeSubscription.add(subscription);
    }

    private void handleSuccessLoadShoppingList(@NonNull ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
        setShoppingListOnView();
    }

    private void handleErrorLoadShoppingList(Throwable throwable) {
        throwable.printStackTrace();
        System.out.println(">>HideProgress");//TODO stub
        System.out.println(">>ShowErorrOnView");
    }

    private void setShoppingListOnView(){
        view.setUpShopingList(shoppingList.getShoppingItems());
        view.setTotalCost(shoppingList.getTotalCost());
        view.setTotalBoughtCost(shoppingList.getTotalBoughtCost());
        view.setListName(shoppingList.getName());
    }


    public void bindView(IShoppingListView view) {
        this.view = view;
    }


    public void unbindView() {
        compositeSubscription.clear();
        view = null;
    }

    @Override
    public List<ShoppingItem> searchShoppingItems(String pattern) {
        return new ArrayList<>(0);//TODO stub
    }
}