package ru.alexandertesbsnko.shoplist3.data_source.net.model.response.packs;

import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.AbstractResponse;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtPackDTO;

public class AtFindPackResponse extends AbstractResponse {
    private List<AtPackDTO> packList;

    public List<AtPackDTO> getPackList() {
        return packList;
    }

    public void setPackList(List<AtPackDTO> packList) {
        this.packList = packList;
    }
}
