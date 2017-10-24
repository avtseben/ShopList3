package ru.alexandertesbsnko.shoplist3.data_source.net.model.dto;


public class AtPackDTO  {
    public Long id;

    public String name;
    public Integer weight;

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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
