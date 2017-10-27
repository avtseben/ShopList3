package ru.alexandertesbsnko.shoplist3.ui.shoping_list.model;

/**
 * Created by avtseben on 22.10.17.
 */

public class Category {
    private final long id;
    private final String name;
    private final String image;

    public Category(long id, String name, String briefName) {
        this.id = id;
        this.name = name;
        this.image = briefName;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
