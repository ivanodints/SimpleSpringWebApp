package ru.geekbrains.service.DTO;

import ru.geekbrains.art_shop.Product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductDTO {

    private Long id;

    @NotEmpty
    private String  category;

    @NotEmpty
    private String title;

    @NotNull
    private Integer price;

    public ProductDTO() {
    }

    public ProductDTO(String category, String title, int price) {
        this.category = category;
        this.title = title;
        this.price = price;
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.category = product.getCategory();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
