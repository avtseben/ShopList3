package ru.alexandertesbsnko.shoplist3.data_source.net.model.response.brands;

import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtBrandDTO;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.AbstractResponse;

import java.util.List;

public class AtFindBrandResponse extends AbstractResponse {
    private List<AtBrandDTO> brandList;

    public List<AtBrandDTO> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<AtBrandDTO> brandList) {
        this.brandList = brandList;
    }
}
