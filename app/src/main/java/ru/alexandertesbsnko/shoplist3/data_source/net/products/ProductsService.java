package ru.alexandertesbsnko.shoplist3.data_source.net.products;


import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.products.AtFindProductRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.prices.AtFindPricesResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.products.AtFindProductResponse;
import rx.Observable;


public interface ProductsService {

    String authBasic64 = "cm9vdDpzZGZsa20zNDUwMzQtd3JlIyR3ZWZld2Y=";

    @Headers({
            "Accept: application/json",
            "Authorization:Basic " + authBasic64
    })
    @POST("atFindProduct")
    Observable<AtFindProductResponse> atFindProductAsync(@Body AtFindProductRequest request);

}