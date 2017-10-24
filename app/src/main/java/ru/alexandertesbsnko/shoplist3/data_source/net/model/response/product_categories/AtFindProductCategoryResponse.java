package ru.alexandertesbsnko.shoplist3.data_source.net.model.response.product_categories;

import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.AbstractResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtProductCategoryDTO;

import java.util.List;

public class AtFindProductCategoryResponse extends AbstractResponse {
    private List<AtProductCategoryDTO> itemList;

    public List<AtProductCategoryDTO> getProductCategoryList() {
        return itemList;
    }

    public void setProductCategoryList(List<AtProductCategoryDTO> itemList) {
        this.itemList = itemList;
    }
}
