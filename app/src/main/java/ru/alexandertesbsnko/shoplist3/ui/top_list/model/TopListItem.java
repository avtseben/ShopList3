package ru.alexandertesbsnko.shoplist3.ui.top_list.model;

/**
 * Класс определяющий список, "поход в пятицу", "для нового года"
 * это набор продуктов и дату составления списка
 */
public class TopListItem {

    private long id;
    private String name;
    private String dateTitle;
    private int imageId;

    public TopListItem(long id, String dateTitle, String name, int imageId) {
        this.id = id;
        this.dateTitle = dateTitle;
        this.name = name;
        this.imageId = imageId;
    }


    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getDateTitle() {
        return dateTitle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
