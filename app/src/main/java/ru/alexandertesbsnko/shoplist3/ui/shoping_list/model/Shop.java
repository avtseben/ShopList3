package ru.alexandertesbsnko.shoplist3.ui.shoping_list.model;

/**
 * Created by avtseben on 22.10.17.
 */

public class Shop {
    private final long id;
    private final String name;

    public Shop(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
