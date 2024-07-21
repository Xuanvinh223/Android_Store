package com.example.store.model;
import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

@Table
public class Product extends SugarRecord {
    @Column(name = "IMAGE_RES_ID")
    @Unique
    private int imageResId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "CATEGORY_ID")
    private Long categoryId; // Để lưu trữ ID của Category

    @Column(name = "RATING")
    private double rating;

    public Product() {

    }

    public Product(int imageResId, String name, int price, Long categoryId, double rating) {
        this.imageResId = imageResId;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.rating = rating;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
