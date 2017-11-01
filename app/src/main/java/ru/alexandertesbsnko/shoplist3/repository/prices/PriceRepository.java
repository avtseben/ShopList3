package ru.alexandertesbsnko.shoplist3.repository.prices;

import java.util.ArrayList;
import java.util.Arrays;

import ru.alexandertesbsnko.shoplist3.data_source.common.AckResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.common.ServiceBuilder;
import ru.alexandertesbsnko.shoplist3.data_source.net.merchandises.MerchandisesService;
import ru.alexandertesbsnko.shoplist3.data_source.net.merchandises.PricesService;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtModifyPriceDTO;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.merchandises.AtUpdateMerchandiseRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.prices.AtUpdatePriceRequest;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import rx.Observable;

/**
 * Created by avtseben on 01.11.17.
 */
public class PriceRepository implements IPriceRepository {
    @Override
    public Observable<AckResponse> updatePrice(ShoppingItem shoppingItem) {
        PricesService service = new ServiceBuilder().buildPricesService();
        AtUpdatePriceRequest request = new AtUpdatePriceRequest();
        AtModifyPriceDTO dto = new AtModifyPriceDTO();
        dto.setMerchandiseId(shoppingItem.getMerchandise().getId());
        dto.setShopId(shoppingItem.getShop().getId());
        dto.setPrice(new Double(shoppingItem.getPrice()).intValue());
        request.setPriceList(Arrays.asList(dto));
        return service.atUpdatePrices(request);
    }
}
