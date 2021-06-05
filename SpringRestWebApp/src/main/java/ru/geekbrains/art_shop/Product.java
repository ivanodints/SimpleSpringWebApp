package ru.geekbrains.art_shop;


import ru.geekbrains.service.ProductRepr;
import ru.geekbrains.service.ProductRest;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String  category;


    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private Integer price;

    public Product() {
    }

    public Product(String category, String title, Integer price) {
        this.category = category;
        this.title = title;
        this.price = price;
    }

    public Product(ProductRepr productRepr) {
        this.id = productRepr.getId();
        this.category = productRepr.getCategory();
        this.title = productRepr.getTitle();
        this.price = productRepr.getPrice();
    }

    public Product(ProductRest productRest) {
        this.id = productRest.getId();
        this.category = productRest.getCategory();
        this.title = productRest.getTitle();
        this.price = productRest.getPrice();
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

    public int getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}