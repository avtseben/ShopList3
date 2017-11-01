package ru.alexandertesbsnko.shoplist3.repository.prices;

import ru.alexandertesbsnko.shoplist3.data_source.common.AckResponse;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import rx.Observable;

public interface IPriceRepository {
    Observable<AckResponse> updatePrice(ShoppingItem shoppingItem);
}
