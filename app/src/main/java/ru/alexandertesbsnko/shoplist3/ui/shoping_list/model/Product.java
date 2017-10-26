package ru.alexandertesbsnko.shoplist3.ui.shoping_list.model;

/**
 * Created by avtseben on 26.10.17.
 */

public class Product {
    private final long id;
    private final String name;
    private final String categoryName;

    public Product(long id, String name, String categoryName) {
        this.id = id;
        this.name = name;
        this.categoryName = categoryName;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
