package ru.alexandertesbsnko.shoplist3.data_source.net.model.response.products;

import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.AbstractResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtProductDTO;
import java.util.List;

public class AtFindProductResponse extends AbstractResponse {
    private List<AtProductDTO> productList;

    public List<AtProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<AtProductDTO> productList) {
        this.productList = productList;
    }
}
