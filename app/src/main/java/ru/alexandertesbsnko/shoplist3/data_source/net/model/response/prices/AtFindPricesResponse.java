package ru.alexandertesbsnko.shoplist3.data_source.net.model.response.prices;


import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.AbstractResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtBrowsePriceDTO;
import java.util.List;

public class AtFindPricesResponse extends AbstractResponse {
    private List<AtBrowsePriceDTO> priceList;

    public List<AtBrowsePriceDTO> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<AtBrowsePriceDTO> priceList) {
        this.priceList = priceList;
    }
}
