package ru.alexandertesbsnko.shoplist3.ui.top_list.presenter;


import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ru.alexandertesbsnko.shoplist3.bussines_domain.shopping_list.IShoppingListInteractor;
import ru.alexandertesbsnko.shoplist3.data_source.common.AckResponse;
import ru.alexandertesbsnko.shoplist3.di.shoping_list.ShoppingListsInteractorProvider;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import ru.alexandertesbsnko.shoplist3.ui.top_list.model.TopListItem;
import ru.alexandertesbsnko.shoplist3.ui.top_list.view.ITopView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class TopPresenter implements ITopPresenter {

    private List<TopListItem> topList;
    private ITopView view;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();//TODO to base class

    IShoppingListInteractor interactor = ShoppingListsInteractorProvider.INSTANCE.provide();

    @Override
    public void loadTopList() {
        Subscription subscription = interactor.loadAllShoppingLists()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ShoppingList>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleUnexpectedError(e);
                    }

                    @Override
                    public void onNext(List<ShoppingList> topList) {
                        handleLoadTopList(topList);
                    }
                });
        compositeSubscription.add(subscription);
    }

    private void handleUnexpectedError(Throwable e){
        e.printStackTrace();
        System.out.println(">>Error");

    }
    private void handleExpectedErrors(AckResponse response) {
        if(response.getState() == AckResponse.State.ERROR){
            view.shopErrorMessage("Ожидаемая проблема. Ты что-то не так делаешь");
        } else {
            System.out.println("All Ok");
        }

    }

    private void handleLoadTopList(List<ShoppingList> topList){
        List<TopListItem> topListItems = new LinkedList<>();
        for (ShoppingList shoppingList : topList) {
            int shopListSize = shoppingList.getShoppingItems().size();
            int imageId =0;
            if(shopListSize >= 1 & shopListSize < 5){
               imageId = 1;
            }
            topListItems.add(
                    new TopListItem(
                            shoppingList.getId()
                            ,"Date"
                            ,shoppingList.getName()
                            ,imageId
                    )
            );
        }
        this.topList = topListItems;
        view.setUpTopList(topListItems);

    }

    @Override
    public void selectExitingShopingList(int position) {
        System.out.println(">>Select: " + position);

    }

    @Override
    public void deleteShopingList(int position) {
        long id = topList.get(position).getId();
        topList.remove(position);
        view.removeItemFromList(position);
        Subscription subscription = interactor.deleteShoppingList(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AckResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleUnexpectedError(e);
                    }

                    @Override
                    public void onNext(AckResponse response) {
                        handleExpectedErrors(response);
                    }

                });
        compositeSubscription.add(subscription);

    }

    @Override
    public void bindView(ITopView view) {
        this.view = view;

    }

    @Override
    public void unbindView() {
        this.view = null;

    }

    @Override
    public void createShoppingList(String listName) {
        interactor.createShoppingList("Список")//TODO Hardcode
        .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShoppingList>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleUnexpectedError(e);
                    }

                    @Override
                    public void onNext(ShoppingList shoppingList) {
                        handleSuccesCreatingList(shoppingList);
                    }

                });
    }

    private void handleSuccesCreatingList(ShoppingList shoppingList){
        //TODO navige to list
    }
}
