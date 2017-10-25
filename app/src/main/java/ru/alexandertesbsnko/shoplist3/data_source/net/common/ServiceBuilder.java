package ru.alexandertesbsnko.shoplist3.data_source.net.common;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.alexandertesbsnko.shoplist3.data_source.net.shopping_list.ShoppingListsService;


public class ServiceBuilder {


    public ShoppingListsService buildShoppingListService(){
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://localhost:8080/")
                .baseUrl("http://10.0.2.2:8080/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new MyGsonBuilder().create()))
                .build();
        return retrofit.create(ShoppingListsService.class);
    }

}