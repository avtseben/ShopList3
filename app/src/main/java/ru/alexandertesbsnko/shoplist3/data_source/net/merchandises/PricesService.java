package ru.alexandertesbsnko.shoplist3.data_source.net.merchandises;


import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import ru.alexandertesbsnko.shoplist3.data_source.common.AckResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.merchandises.AtUpdateMerchandiseRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.prices.AtUpdatePriceRequest;
import rx.Observable;


public interface PricesService {

    String authBasic64 = "cm9vdDpzZGZsa20zNDUwMzQtd3JlIyR3ZWZld2Y=";

    @Headers({
            "Accept: application/json",
            "Authorization:Basic " + authBasic64
    })
    @POST("atUpdatePrice")
    Observable<AckResponse> atUpdatePrices(@Body AtUpdatePriceRequest request);

}