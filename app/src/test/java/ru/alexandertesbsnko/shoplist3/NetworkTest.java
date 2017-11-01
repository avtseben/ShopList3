package ru.alexandertesbsnko.shoplist3;


import org.junit.Test;


import java.util.List;

import retrofit2.Call;
import ru.alexandertesbsnko.shoplist3.bussines_domain.shopping_list.ShoppingListInteractor;
import ru.alexandertesbsnko.shoplist3.data_source.common.AckResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.common.ServiceBuilder;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShoppingItemDTO;
import ru.alexandertesbsnko.shoplist3.data_source.net.shopping_list.ShoppingListsService;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtFindShoppingListsRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists.AtFindShoppingListsResponse;
import ru.alexandertesbsnko.shoplist3.repository.products.IProductsRepository;
import ru.alexandertesbsnko.shoplist3.repository.products.ProductsRepository;
import ru.alexandertesbsnko.shoplist3.repository.shopping_list.AsyncRestShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Product;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Shop;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
//import ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter.RestPresenterImpl;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter.ShoppingListPresenter;
import rx.Subscriber;

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




//    @Test
//    public void testPresenterProductSearch() {
//        new ShoppingListPresenter().searchProductsByName("Мол");
//    }

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

        ShoppingListInteractor interactor = new ShoppingListInteractor();
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

    @Test
    public void testUpdateShoppingItemRepository() {
        AsyncRestShoppingListRepository repository = new AsyncRestShoppingListRepository();
        AtShoppingItemDTO updateDto = new AtShoppingItemDTO();
        updateDto.setId(1l);
        updateDto.setState(2);
        repository.updateShoppingItem(updateDto)
                .subscribe(
                        new Subscriber<AckResponse>() {
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
                            public void onNext(AckResponse ackResponse) {
                                System.out.println(ackResponse.getState());
                            }
                        }
                );
    }

    @Test
    public void testCreateShoppingList() {
        AsyncRestShoppingListRepository repository = new AsyncRestShoppingListRepository();
        repository.createShoppingList("Test4")
                .subscribe(
                        new Subscriber<ShoppingList>() {
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
                            public void onNext(ShoppingList shoppingList) {
                                System.out.println(shoppingList.getId());
                                System.out.println(shoppingList.getName());
                            }
                        }
                );
    }

    @Test
    public void testDeleteShoppingList() {
        AsyncRestShoppingListRepository repository = new AsyncRestShoppingListRepository();
        repository.deleteShoppingList(7l)
                .subscribe(
                        new Subscriber<AckResponse>() {
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
                            public void onNext(AckResponse ackResponse) {
                                System.out.println(ackResponse.getState());
                            }
                        }
                );
    }

    @Test
    public void testLoadAllShoppingLists() {
        AsyncRestShoppingListRepository repository = new AsyncRestShoppingListRepository();
        repository.loadAllShoppingLists()
                .subscribe(
                        new Subscriber<List<ShoppingList>>() {
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
                            public void onNext(List<ShoppingList> listOfLists) {
                                System.out.println(listOfLists.size());
                                System.out.println();
                            }
                        }
                );
    }

}