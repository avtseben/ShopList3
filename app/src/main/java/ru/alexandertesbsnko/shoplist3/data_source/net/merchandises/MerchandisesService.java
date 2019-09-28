package ru.alexandertesbsnko.shoplist3.data_source.net.merchandises;


import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import ru.alexandertesbsnko.shoplist3.data_source.common.AckResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.merchandises.AtUpdateMerchandiseRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.products.AtFindProductRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.merchandises.AtUpdateMerchandiseResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.products.AtFindProductResponse;
import rx.Observable;


public interface MerchandisesService {

    String authBasic64 = "cm9vdDpzZGZsa20zNDUwMzQtd3JlIyR3ZWZld2Y=";

    @Headers({
            "Content-type:application/json",
            "Authorization:Basic " + authBasic64
    })
    @POST("atUpdateMerchandise")
    Observable<AckResponse> atUpdateMerchandise(@Body AtUpdateMerchandiseRequest request);

}