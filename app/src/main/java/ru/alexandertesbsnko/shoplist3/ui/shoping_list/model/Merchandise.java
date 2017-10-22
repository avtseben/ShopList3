package ru.alexandertesbsnko.shoplist3.ui.shoping_list.model;

/**
 * Created by avtseben on 22.10.17.
 */

public class Merchandise {
    private final long id;
    private final Category category;
    private final String name;

    public Merchandise(long id, Category category, String name) {
        this.id = id;
        this.category = category;
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
