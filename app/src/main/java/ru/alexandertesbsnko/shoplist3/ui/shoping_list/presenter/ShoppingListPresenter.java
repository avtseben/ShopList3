package ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter;


import android.support.annotation.NonNull;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import ru.alexandertesbsnko.shoplist3.bussines_domain.shopping_list.IShoppingListInteractor;
import ru.alexandertesbsnko.shoplist3.bussines_domain.shopping_list.ShoppingListInteractor;
import ru.alexandertesbsnko.shoplist3.data_source.common.AckResponse;
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
public class ShoppingListPresenter implements IShoppingListPresenter {

    private IShoppingListView view;
    IShoppingListInteractor interactor = ShoppingListsInteractorProvider.INSTANCE.provide();
    private ShoppingList shoppingList;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    public void loadShoppingList(long id) {
        loadShoppingListFromData(id);
    }

    @Override
    public void incrementQuantity(long shoppingItemId) {//TODO оптимизировать в мапу
        ShoppingItem theItem = null;
        List<ShoppingItem> items = shoppingList.getShoppingItems();
        for (ShoppingItem item : items) {
            if (item.getId() == shoppingItemId) {
                item.increaseQuantity();
                theItem = item;
            }
        }
        view.setTotalCost(shoppingList.getTotalCost());
        if(theItem == null){
            return;
        }
        updateQuantityInDataLayer(theItem);
    }

    private void updateQuantityInDataLayer(ShoppingItem theItem) {
        Subscription subscription = interactor.updateShoppingItemQuantity(theItem)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AckResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleUexpectedError(e);
                    }

                    @Override
                    public void onNext(AckResponse ackResponse) {
                        handleExpectedErrors(ackResponse);
                    }
                });
        compositeSubscription.add(subscription);
    }

    @Override
    public void decrementQuantity(long shoppingItemId) {
        ShoppingItem theItem = null;
        List<ShoppingItem> items = shoppingList.getShoppingItems();
        for (ShoppingItem item : items) {
            if (item.getId() == shoppingItemId) {
                boolean isChanged = item.decreaseQuantity();
                if(!isChanged){
                    return;
                }
                theItem = item;
            }
        }
        view.setTotalCost(shoppingList.getTotalCost());
        if(theItem == null){
            return;
        }
        updateQuantityInDataLayer(theItem);
    }

    public void buyShoppingItem(long id) {
        List<ShoppingItem> items = shoppingList.getShoppingItems();
        for (ShoppingItem item : items) {
            if (item.getId() == id) {
                item.setState(ShoppingItem.BOUGHT);
            }
        }
        view.setTotalBoughtCost(shoppingList.getTotalBoughtCost());
        Subscription subscription = interactor.buyShoppingItem(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AckResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleUexpectedError(e);
                    }

                    @Override
                    public void onNext(AckResponse ackResponse) {
                        handleExpectedErrors(ackResponse);
                    }
                });
        compositeSubscription.add(subscription);
    }

    public void deleteShoppingItem(long id) {
        List<ShoppingItem> items = shoppingList.getShoppingItems();
        for (ShoppingItem item : items) {
            if (item.getId() == id) {
                item.setState(ShoppingItem.DELETED);
            }
        }
        view.setTotalCost(shoppingList.getTotalCost());
        Subscription subscription = interactor.deleteShoppingItem(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AckResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleUexpectedError(e);
                    }

                    @Override
                    public void onNext(AckResponse ackResponse) {
                        handleExpectedErrors(ackResponse);
                    }
                });
        compositeSubscription.add(subscription);
    }

    @Override
    public void updatePrice(final ShoppingItem shoppingItem, double newPrice) {
        final double oldPrice = shoppingItem.getPrice();
        shoppingItem.setPrice(newPrice);
        updateCostsOnView();
        //TODO обновляется только один Shopping Item. Баг архитектуры
        Subscription subscription = interactor.updatePrice(shoppingItem)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AckResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        shoppingItem.setPrice(oldPrice);
                        view.shopErrorMessage("Не получилось обновить цену");
                        updateCostsOnView();
                    }

                    @Override
                    public void onNext(AckResponse response) {
                       //NOP
                    }
                });
        compositeSubscription.add(subscription);

    }


    private void loadShoppingListFromData(long id) {
        Subscription subscription = interactor.loadShoppingListById(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShoppingList>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleUexpectedError(e);
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

    private void handleExpectedErrors(AckResponse response) {
        if(response.getState() == AckResponse.State.ERROR){
            view.shopErrorMessage("Ожидаемая проблема. Ты что-то не так делаешь");
        } else {
            System.out.println("All Ok");
        }

    }

    private void handleUexpectedError(Throwable throwable) {
        throwable.printStackTrace();
        view.shopErrorMessage("Ой! Неожиданная проблема. Возможно нет связи с сервером");
    }

    private void handleErrorLoadShoppingList(Throwable throwable) {
        throwable.printStackTrace();
        view.shopErrorMessage("Ой! Что-то сломалось. Возможно нет связи с сервером");
    }

    private void handleErrorLoadProducts(Throwable throwable) {
        throwable.printStackTrace();
        view.hideSearchProgress();
        view.shopErrorMessage("Ой! Что-то сломалось. Возможно нет связи с сервером");
    }

    private void handleErrorInsertNewShoppingItem(Throwable throwable) {
        throwable.printStackTrace();
        view.shopErrorMessage("Ой! Что-то сломалось. Возможно нет связи с сервером");

    }

    private void setShoppingListOnView() {
        List<ShoppingItem> onlyInListStateItems = new ArrayList<>();
        for (ShoppingItem shoppingItem : shoppingList.getShoppingItems()) {
            if(shoppingItem.getState() == ShoppingItem.IN_LIST){
                onlyInListStateItems.add(shoppingItem);//View имеет дело только со элементами с нужным статусом
            }
        }
        view.setUpShopingList(onlyInListStateItems);
        updateCostsOnView();
        view.setListName(shoppingList.getName());
    }

    private void updateCostsOnView(){
        view.setTotalCost(shoppingList.getTotalCost());
        view.setTotalBoughtCost(shoppingList.getTotalBoughtCost());
    }


    public void bindView(IShoppingListView view) {
        this.view = view;
    }


    public void unbindView() {
        compositeSubscription.clear();
        view = null;
    }

    @Override
    public List<Product> searchProductsByNameSync(String pattern) {
        view.showSearchProgress();
        final List<Product> founded = new ArrayList<>();
        final CountDownLatch latch = new CountDownLatch(1);
        Subscription subscription = interactor.searchProductsByName(pattern)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Product>>() {
                    @Override
                    public void onCompleted() {
                        latch.countDown();
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleErrorLoadProducts(e);
                        latch.countDown();
                    }

                    @Override
                    public void onNext(List<Product> products) {
                        founded.addAll(products);
                        latch.countDown();
                    }
                });
        compositeSubscription.add(subscription);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            view.shopErrorMessage("Нас прервали! Повтори еще");
        }
        view.hideSearchProgress();
        return founded;
    }

    @Override
    public void addProduct(Product product) {
        Subscription subscription = interactor.insertItemToShoppingList(shoppingList.getId(), product.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShoppingItem>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleErrorInsertNewShoppingItem(e);
                    }

                    @Override
                    public void onNext(ShoppingItem shoppingItem) {
                        handleSuccessInsertShoppingItem(shoppingItem);
                    }
                });
        compositeSubscription.add(subscription);
    }

    private void handleSuccessInsertShoppingItem(ShoppingItem newShoppingItem) {
        if(newShoppingItem == null){
            view.shopErrorMessage("Не удалось добавить в список. " +
                    "Мы попробовали создать покупку по названию продукта," +
                    " но от сервера пришёл null. Возможно нет цены для данного продукта");
            return;
        }
        shoppingList.getShoppingItems().add(newShoppingItem);
        view.addShoppingItem(newShoppingItem);
        view.setTotalCost(shoppingList.getTotalCost());
    }
}