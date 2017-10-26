package ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter;


import android.support.annotation.NonNull;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.alexandertesbsnko.shoplist3.bussines_domain.shopping_list.IShoppingListInteractor;
import ru.alexandertesbsnko.shoplist3.bussines_domain.shopping_list.ShoppingListInteractor;
import ru.alexandertesbsnko.shoplist3.di.shoping_list.ShoppingListsInteractorProvider;
import ru.alexandertesbsnko.shoplist3.repository.shopping_list.AsyncRestShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.repository.shopping_list.IShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Category;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Merchandise;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Product;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Shop;
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
    IShoppingListInteractor interactor = ShoppingListsInteractorProvider.INSTANCE.provide();
    private ShoppingList shoppingList;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    public void loadShoppingList(){
        loadShoppingListFromData(1);
    }

    @Override
    public void incrementQuantity(long shoppingItemId) {//TODO оптимизировать в мапу
        List<ShoppingItem> items = shoppingList.getShoppingItems();
        for (ShoppingItem item : items) {
            if(item.getId() == shoppingItemId){
                item.increaseQuantity();
            }
        }
        view.setTotalCost(shoppingList.getTotalCost());
    }

    @Override
    public void decrementQuantity(long shoppingItemId) {
        List<ShoppingItem> items = shoppingList.getShoppingItems();
        for (ShoppingItem item : items) {
            if(item.getId() == shoppingItemId){
                item.decreaseQuantity();
            }
        }
        view.setTotalCost(shoppingList.getTotalCost());
    }

    public void buyShoppingItem(long id){
        List<ShoppingItem> items = shoppingList.getShoppingItems();
        for (ShoppingItem item : items) {
            if(item.getId() == id){
                item.setState(ShoppingItem.BOUGHT);
            }
        }
        view.setTotalBoughtCost(shoppingList.getTotalBoughtCost());
    }

    public void deleteShoppingItem(long id){
        List<ShoppingItem> items = shoppingList.getShoppingItems();
        for (ShoppingItem item : items) {
            if(item.getId() == id){
                item.setState(ShoppingItem.DELETED);
            }
        }
        view.setTotalCost(shoppingList.getTotalCost());
    }


    private void loadShoppingListFromData(long id) {
        Subscription subscription = new AsyncRestShoppingListRepository().loadShoppingListById(id)//TODO обращаться к интерфейсу
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

    private void handleErrorLoadProducts(Throwable throwable) {
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
    public void searchProductsByName(String pattern) {
        Subscription subscription  = interactor.searchProductsByName(pattern)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Product>>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        handleErrorLoadProducts(e);
                    }

                    @Override
                    public void onNext(List<Product> products) {
                        handleSuccessLoadProducts(products);
                    }
                });
        compositeSubscription.add(subscription);
    }

    private void handleSuccessLoadProducts(@NonNull List<Product> products) {
        setFindedProductsOnView(products);
    }

    private void setFindedProductsOnView(List<Product> items){
        view.setFindedProducts(items);
    }

    @Override
    public void addProduct(Product product) {
        //TODO fake
        ShoppingItem newShoppingItem = new ShoppingItem(
                100
                ,new Shop(100,"Лента")
                ,new Merchandise(100
                    ,new Category(1,product.getCategoryName(),"milk")
                   ,product.getName() )
                ,100d);
        //
        //TODO обратится к интерактору за shopping Item'ом для продукта
        shoppingList.getShoppingItems().add(newShoppingItem);
        view.addShoppingItem(newShoppingItem);
        view.setTotalCost(shoppingList.getTotalCost());
    }
}