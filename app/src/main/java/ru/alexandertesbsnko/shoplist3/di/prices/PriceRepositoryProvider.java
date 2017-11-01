package ru.alexandertesbsnko.shoplist3.di.prices;

import ru.alexandertesbsnko.shoplist3.repository.prices.IPriceRepository;
import ru.alexandertesbsnko.shoplist3.repository.prices.PriceRepository;

/**
 * Created by avtseben on 26.10.17.
 */

public class PriceRepositoryProvider {

    public static PriceRepositoryProvider INSTANCE = new PriceRepositoryProvider();

    private PriceRepositoryProvider(){}

    public IPriceRepository provide(){
        return new PriceRepository();
    }
}
