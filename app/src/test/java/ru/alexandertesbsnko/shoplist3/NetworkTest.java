package ru.alexandertesbsnko.shoplist3;


import org.junit.Test;


import javax.annotation.Resource;

import retrofit2.Call;
import ru.alexandertesbsnko.shoplist3.data_source.net.common.ServiceBuilder;
import ru.alexandertesbsnko.shoplist3.data_source.net.shopping_list.ShoppingListsService;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtFindShoppingListsRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists.AtFindShoppingListsResponse;
import ru.alexandertesbsnko.shoplist3.repository.shopping_list.RestShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NetworkTest {
    @Test
    public void testFindShoppingListsApiEndpoint() throws Exception {
        ShoppingListsService service = new ServiceBuilder().buildShoppingListService();
        AtFindShoppingListsRequest request = new AtFindShoppingListsRequest();
        MySubscriber subscriber = new MySubscriber();
        request.setId(1l);
        Observable<AtFindShoppingListsResponse> observable0 = service.atFindShoppingListsAsync(request);
        Observable<AtFindShoppingListsResponse> observable = service.atFindShoppingListsAsync(request);
        observable
                .subscribeOn(Schedulers.io())
                .mergeWith(observable0)
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber
//                        new Subscriber<AtFindShoppingListsResponse>() {
//                    @Override
//                    public final void onCompleted() {
//                        System.out.println("Completed");
//                    }
//
//                    @Override
//                    public final void onError(Throwable e) {
//                        System.out.println("Error");
//                    }
//
//                    @Override
//                    public void onNext(AtFindShoppingListsResponse response) {
//                        System.out.println(response.getShoppingLists().get(0).getName());
//                        System.out.println();
//
//                    }
//                }
                );
        Subscription subscription = observable0.subscribe(subscriber);
        subscription.unsubscribe();

    }

    class MySubscriber extends Subscriber {
        @Override
        public final void onCompleted() {
            System.out.println("Completed");
        }

        @Override
        public final void onError(Throwable e) {
            System.out.println("Error");
        }

        @Override
        public void onNext(Object o) {
            System.out.println(((AtFindShoppingListsResponse) o).getShoppingLists().get(0).getName());
            System.out.println();
        }
    }

    private void hello() {
        System.out.println("hello");
    }

    @Test
    public void testFindShoppingListsRestRepository() throws Exception {
        ShoppingListsService service = new ServiceBuilder().buildShoppingListService();
        AtFindShoppingListsRequest request = new AtFindShoppingListsRequest();
        request.setId(1l);
        Call<AtFindShoppingListsResponse> call = service.atFindShoppingLists(request);
        call.execute().body();
    }


    @Test
    public void testFindShoppingListsRestRepositoryAdapter() throws Exception {
        RestShoppingListRepository repository = new RestShoppingListRepository();
        ShoppingList list = repository.loadShoppingListById(1l);
        System.out.println();
    }

    @Test
    public void testObservable() {
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {

                                                      @Override
                                                      public void call(Subscriber<? super String> subscriber) {
                                                          subscriber.onNext("Hello");
                                                          subscriber.onCompleted();
                                                          subscriber.onError(new Exception());

                                                      }
                                                  }
        );
        observable.skip(0);
        System.out.println();

    }

}