package ru.alexandertesbsnko.shoplist3;


import org.junit.Test;


import javax.annotation.Resource;

import retrofit2.Call;
import ru.alexandertesbsnko.shoplist3.data_source.net.common.ServiceBuilder;
import ru.alexandertesbsnko.shoplist3.data_source.net.shopping_list.ShoppingListsService;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtFindShoppingListsRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists.AtFindShoppingListsResponse;
import ru.alexandertesbsnko.shoplist3.repository.shopping_list.AsyncRestShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.repository.shopping_list.RestShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter.RestPresenterImpl;
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
        request.setId(1l);
        service.atFindShoppingListsAsync(request)
                .subscribe(new Subscriber<AtFindShoppingListsResponse>() {
                    @Override
                    public final void onCompleted() {
                    }

                    @Override
                    public final void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(AtFindShoppingListsResponse response) {
                        System.out.println(response);
                    }
                });
    }
    @Test
    public void testAsyncRepo() throws Exception {

        AsyncRestShoppingListRepository repo = new AsyncRestShoppingListRepository();
        repo.loadShoppingListById(1)
                .subscribe(new Subscriber<ShoppingList>() {
                    @Override
                    public final void onCompleted() {
                    }

                    @Override
                    public final void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ShoppingList shoppingList) {
                        System.out.println(shoppingList.getName());
                    }
                });
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
    public void testAsyncPresenter() {
        RestPresenterImpl presenter = new RestPresenterImpl();
/*        presenter.asyncLoadShoppingListById(1l)
                .subscribe(new Subscriber<ShoppingList>() {
                    @Override
                    public void onCompleted() {
                        System.out.println(">>Completed!!!!");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShoppingList shoppingList) {

                        System.out.println(">>List loaded!!!!");
                        System.out.println(shoppingList.getName());
//                        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(shoppingList.getName());
                    }
                });*/

    }

}