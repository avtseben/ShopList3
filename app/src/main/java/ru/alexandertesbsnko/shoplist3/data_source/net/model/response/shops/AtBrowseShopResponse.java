package ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shops;

import ru.alexandertsebenko.shoplist.api.dto.AtShopDTO;
import ru.rtcomm.platform.api.response.RtAbstractBrowseResponse;
import ru.rtcomm.platform.api.response.AbstractResponse;

import java.util.List;

public class AtBrowseShopResponse extends RtAbstractBrowseResponse {
    private List<AtShopDTO> shopList;

    public List<AtShopDTO> getShopList() {
        return shopList;
    }

    public void setShopList(List<AtShopDTO> shopList) {
        this.shopList = shopList;
    }
}
