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

    public Product() {

    }

    public Product(int imageResId, String name, int price, Long categoryId) {
        this.imageResId = imageResId;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
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
}
