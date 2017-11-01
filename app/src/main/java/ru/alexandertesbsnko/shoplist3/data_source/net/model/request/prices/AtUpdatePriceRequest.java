package ru.alexandertesbsnko.shoplist3.data_source.net.model.request.prices;

import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtModifyPriceDTO;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.AbstractRequest;

import java.util.List;

public class AtUpdatePriceRequest extends AbstractRequest {
    List<AtModifyPriceDTO> priceList;

    public List<AtModifyPriceDTO> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<AtModifyPriceDTO> priceList) {
        this.priceList = priceList;
    }
}
