package ru.alexandertesbsnko.shoplist3.ui.top_list.presenter;


import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import ru.alexandertesbsnko.shoplist3.bussines_domain.shopping_list.IShoppingListInteractor;
import ru.alexandertesbsnko.shoplist3.data_source.common.AckResponse;
import ru.alexandertesbsnko.shoplist3.di.router.RouterProvider;
import ru.alexandertesbsnko.shoplist3.di.shoping_list.ShoppingListsInteractorProvider;
import ru.alexandertesbsnko.shoplist3.ui.router.IRouter;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import ru.alexandertesbsnko.shoplist3.ui.top_list.model.TopListItem;
import ru.alexandertesbsnko.shoplist3.ui.top_list.view.ITopView;
import ru.alexandertesbsnko.shoplist3.util.DateBuilder;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class TopPresenter implements ITopPresenter {

    private List<TopListItem> topList;
    private ITopView view;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();//TODO to base class
    private IRouter router = RouterProvider.INSTANCE.getRouter();

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
        Log.e("net",e.getMessage());
        e.printStackTrace();
        view.shopErrorMessage("Ой! Неожиданная проблема. Возможно нет связи с сервером");
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
            TopListItem.SizeState sizeState = TopListItem.SizeState.EMPTY;
            if(shopListSize >= 1 & shopListSize < 5){
                sizeState = TopListItem.SizeState.SMALL;
            } else if(shopListSize >= 5 & shopListSize < 10){
                sizeState = TopListItem.SizeState.MEDIUM;
            } else if(shopListSize >= 10){
                sizeState = TopListItem.SizeState.BIG;
            }
            topListItems.add(
                    new TopListItem(
                            shoppingList.getId()
                            , DateBuilder.timeTitleBuilder(shoppingList.getDate().getTime())
                            ,shoppingList.getName()
                            ,sizeState
                    )
            );
        }
        this.topList = topListItems;
        view.setUpTopList(topListItems);

    }

    @Override
    public void selectExitingShopingList(int position) {
        long id = topList.get(position).getId();
        System.out.println(">>Select: " + position + " id: " + id);
        //TODO navigate to list
        router.navigate(IRouter.Screen.SHOPING_LIST,id);

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
        router.navigate(IRouter.Screen.SHOPING_LIST,shoppingList.getId());
    }
}
