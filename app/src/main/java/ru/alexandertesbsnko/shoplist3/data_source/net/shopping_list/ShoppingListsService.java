package ru.alexandertesbsnko.shoplist3.data_source.net.shopping_list;


import retrofit2.Call;
import retrofit2.http.Headers;
import ru.alexandertesbsnko.shoplist3.data_source.common.AckResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtCreateShoppingListRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtDeleteShoppingListRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtInsertItemToShoppingListRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtUpdateShoppingItemsRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists.AtCreateShoppingListResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists.AtInsertItemToShoppingListResponse;
import rx.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtFindShoppingListsRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists.AtFindShoppingListsResponse;


public interface ShoppingListsService {

    String authBasic64 = "cm9vdDpzZGZsa20zNDUwMzQtd3JlIyR3ZWZld2Y=";

    @Headers({
            "Content-type:application/json",
            "Authorization:Basic " + authBasic64
    })
    @POST("atFindShoppingLists")
    Observable<AtFindShoppingListsResponse> atFindShoppingListsAsync(@Body AtFindShoppingListsRequest request);

    @Headers({
            "Content-type:application/json",
            "Authorization:Basic " + authBasic64
    })
    @POST("atInsertItemToShoppingList")
    Observable<AtInsertItemToShoppingListResponse> atInsertItemToShoppingList(@Body AtInsertItemToShoppingListRequest request);


    @Headers({
            "Content-type:application/json",
            "Authorization:Basic " + authBasic64
    })
    @POST("atFindShoppingLists")
    Call<AtFindShoppingListsResponse> atFindShoppingLists(@Body AtFindShoppingListsRequest request);

    @Headers({
            "Content-type:application/json",
            "Authorization:Basic " + authBasic64
    })
    @POST("atUpdateShoppingItems")
    Observable<AckResponse> atUpdateShoppingItems(@Body AtUpdateShoppingItemsRequest request);

    @Headers({
            "Content-type:application/json",
            "Authorization:Basic " + authBasic64
    })
    @POST("atDeleteShoppingList")
    Observable<AckResponse> atDeleteShoppingList(@Body AtDeleteShoppingListRequest request);

    @Headers({
            "Content-type:application/json",
            "Authorization:Basic " + authBasic64
    })
    @POST("atCreateShoppingList")
    Observable<AtCreateShoppingListResponse> atCreateShoppingList(@Body AtCreateShoppingListRequest request);
}