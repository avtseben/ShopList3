package ru.alexandertesbsnko.shoplist3.repository.merchandises;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.data_source.common.AckResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShoppingItemDTO;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import rx.Observable;

public interface IMerchandiseRepository {
    Observable<AckResponse> updateMerchandisePrice(long merchandiseId, double newPrice);
}
