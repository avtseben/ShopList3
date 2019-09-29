package ru.alexandertesbsnko.shoplist3.data_source.net.common;

import android.content.Context;

import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.data_source.net.merchandises.MerchandisesService;
import ru.alexandertesbsnko.shoplist3.data_source.net.merchandises.PricesService;
import ru.alexandertesbsnko.shoplist3.data_source.net.products.ProductsService;
import ru.alexandertesbsnko.shoplist3.data_source.net.shopping_list.ShoppingListsService;
import ru.alexandertesbsnko.shoplist3.di.SandBox;


public class ServiceBuilder {

    private final String BASE_URL = "https://oknebest.site/shoplist/";
    private OkHttpClient client;

    public ServiceBuilder() {
    }

    public ShoppingListsService buildShoppingListService() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new MyGsonBuilder().create()))
                .build();
        return retrofit.create(ShoppingListsService.class);
    }

    public ProductsService buildProductsService() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new MyGsonBuilder().create()))
                .build();
        return retrofit.create(ProductsService.class);
    }

    public MerchandisesService buildMerchandisesService() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new MyGsonBuilder().create()))
                .build();
        return retrofit.create(MerchandisesService.class);
    }

    public PricesService buildPricesService() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new MyGsonBuilder().create()))
                .build();
        return retrofit.create(PricesService.class);
    }

    private OkHttpClient client() {
        if(null == client) {
            this.client = buildCustmClient();
        }
        return this.client;
    }

    private OkHttpClient buildCustmClient() {
        try {
            KeyStore keyStore = readKeyStore(); //your method to obtain KeyStore
            SSLContext sslContext = SSLContext.getInstance("SSL");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, "123456".toCharArray());
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
            return new OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.getSocketFactory())
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    private KeyStore readKeyStore() throws Exception {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());

        // get user password and file input stream
        char[] password = "123456".toCharArray();

        java.io.InputStream fis = null;
        Context context = (Context) SandBox.INSTANCE.get(Context.class);
        try {
            fis = context.getResources().openRawResource(R.raw.mcloud);
            ks.load(fis, password);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return ks;
    }

}