package ru.alexandertesbsnko.shoplist3;


import org.junit.Test;


import java.util.List;

import javax.annotation.Resource;

import retrofit2.Call;
import ru.alexandertesbsnko.shoplist3.bussines_domain.shopping_list.NewShoppingListInteractor;
import ru.alexandertesbsnko.shoplist3.data_source.net.common.ServiceBuilder;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.products.AtFindProductRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.products.AtFindProductResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.products.ProductsService;
import ru.alexandertesbsnko.shoplist3.data_source.net.shopping_list.ShoppingListsService;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtFindShoppingListsRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists.AtFindShoppingListsResponse;
import ru.alexandertesbsnko.shoplist3.repository.products.IProductsRepository;
import ru.alexandertesbsnko.shoplist3.repository.products.ProductsRepository;
import ru.alexandertesbsnko.shoplist3.repository.shopping_list.AsyncRestShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Product;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
//import ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter.RestPresenterImpl;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter.AnotherRestPresenterImpl;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NetworkTest {
    int onNextCount = 0;
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
    public void testPresenterProductSearch() {
        new AnotherRestPresenterImpl().searchProductsByName("Мол");
    }

    @Test
    public void testAsyncProductRepository() {
        IProductsRepository repository = new ProductsRepository();
        repository.searchProductsByNameLike("%о%")
                .subscribe(new Subscriber<List<Product>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Complete");

                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        System.out.println("Fail");
                    }
                    @Override
                    public void onNext(List<Product> products) {
                        for (Product product : products) {
                            System.out.println(product.getName());
                        }
                    }
                });
    }

    @Test
    public void testNewInteractor() {

        NewShoppingListInteractor interactor = new NewShoppingListInteractor();
        interactor.searchProductsByName("сл")
                .subscribe(new Subscriber<List<Product>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Completed!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        System.out.println("Error!");
                    }

                    @Override
                    public void onNext(List<Product> products) {
                        onNextCount++;
                        System.out.println("OnNext!");
                        for (Product product : products) {
                            System.out.println(product.getName());
                        }
                        System.out.println(onNextCount);
                    }
                });

    }
    @Test
    public void testInsertItemToShoppingListRepository() {
        AsyncRestShoppingListRepository repository = new AsyncRestShoppingListRepository();

        repository.insertItemToShoppingList(1,14)
                .subscribe(new Subscriber<ShoppingItem>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        System.out.println("Error");
                    }

                    @Override
                    public void onNext(ShoppingItem shoppingItem) {
                        System.out.println(shoppingItem.getMerchandise().getName());
                    }
                });
    }

}