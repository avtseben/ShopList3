package ru.alexandertesbsnko.shoplist3.repository.merchandises;

import ru.alexandertesbsnko.shoplist3.data_source.common.AckResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.common.ServiceBuilder;
import ru.alexandertesbsnko.shoplist3.data_source.net.merchandises.MerchandisesService;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.merchandises.AtUpdateMerchandiseRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtUpdateShoppingItemsRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.shopping_list.ShoppingListsService;
import rx.Observable;

/**
 * Created by avtseben on 01.11.17.
 */
public class MerchandiseRepository implements IMerchandiseRepository {
    @Override
    public Observable<AckResponse> updateMerchandisePrice(long merchandiseId, double newPrice) {
        MerchandisesService service = new ServiceBuilder().buildMerchandisesService();
        AtUpdateMerchandiseRequest request = new AtUpdateMerchandiseRequest();
        request.setId(merchandiseId);
        request.setPrice(newPrice);
        return service.atUpdateMerchandise(request);
    }
}
