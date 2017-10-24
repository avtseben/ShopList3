package ru.alexandertesbsnko.shoplist3.data_source.net.model.dto;

public class AtMerchandiseDTO {
    private Long id;
    private AtProductCategoryDTO category;
    private AtProductDTO product;
    private AtBrandDTO brand;
    private AtPackDTO pack;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AtProductCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(AtProductCategoryDTO category) {
        this.category = category;
    }

    public AtProductDTO getProduct() {
        return product;
    }

    public void setProduct(AtProductDTO product) {
        this.product = product;
    }

    public AtBrandDTO getBrand() {
        return brand;
    }

    public void setBrand(AtBrandDTO brand) {
        this.brand = brand;
    }

    public AtPackDTO getPack() {
        return pack;
    }

    public void setPack(AtPackDTO pack) {
        this.pack = pack;
    }
}
