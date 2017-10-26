package ru.alexandertesbsnko.shoplist3.data_source.net.shopping_list;


import retrofit2.Call;
import retrofit2.http.Headers;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtInsertItemToShoppingListRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists.AtInsertItemToShoppingListResponse;
import rx.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtFindShoppingListsRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists.AtFindShoppingListsResponse;


public interface ShoppingListsService {

    String authBasic64 = "cm9vdDpzZGZsa20zNDUwMzQtd3JlIyR3ZWZld2Y=";

    @Headers({
            "Accept: application/json",
            "Authorization:Basic " + authBasic64
    })
    @POST("atFindShoppingLists")
    Observable<AtFindShoppingListsResponse> atFindShoppingListsAsync(@Body AtFindShoppingListsRequest request);

    @Headers({
            "Accept: application/json",
            "Authorization:Basic " + authBasic64
    })
    @POST("atInsertItemToShoppingList")
    Observable<AtInsertItemToShoppingListResponse> atInsertItemToShoppingList(@Body AtInsertItemToShoppingListRequest request);


    @Headers({
            "Accept: application/json",
            "Authorization:Basic " + authBasic64
    })
    @POST("atFindShoppingLists")
    Call<AtFindShoppingListsResponse> atFindShoppingLists(@Body AtFindShoppingListsRequest request);
}