package ru.alexandertesbsnko.shoplist3.data_source.net.model.dto;

public class AtProductCategoryDTO {
    private Long id;
    private String name;
    private String briefName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String briefName) {
        this.name = briefName;
    }

    public String getBriefName() {
        return briefName;
    }

    public void setBriefName(String briefName) {
        this.briefName = briefName;
    }
}
