package ru.alexandertesbsnko.shoplist3.data_source.net.model.dto;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.List;

public class AtShoppingListDTO {
    private Long id;
    @Expose
    private Date date;
    private String name;
    private List<AtShoppingItemDTO> shoppingItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AtShoppingItemDTO> getShoppingItems() {
        return shoppingItems;
    }

    public void setShoppingItems(List<AtShoppingItemDTO> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }
}
