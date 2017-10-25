package ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shops;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShopDTO;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.AbstractResponse;

public class AtFindShopResponse extends AbstractResponse {
    private List<AtShopDTO> shopList;

    public List<AtShopDTO> getShopList() {
        return shopList;
    }

    public void setShopList(List<AtShopDTO> shopList) {
        this.shopList = shopList;
    }
}
