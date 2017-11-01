package ru.alexandertesbsnko.shoplist3.di.merchandises;

import ru.alexandertesbsnko.shoplist3.repository.merchandises.IMerchandiseRepository;
import ru.alexandertesbsnko.shoplist3.repository.merchandises.MerchandiseRepository;
import ru.alexandertesbsnko.shoplist3.repository.products.IProductsRepository;
import ru.alexandertesbsnko.shoplist3.repository.products.ProductsRepository;

/**
 * Created by avtseben on 26.10.17.
 */

public class MerchandiseRepositoryProvider {

    public static MerchandiseRepositoryProvider INSTANCE = new MerchandiseRepositoryProvider();

    private MerchandiseRepositoryProvider(){}

    public IMerchandiseRepository provide(){
        return new MerchandiseRepository();
    }
}
