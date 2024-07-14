package com.example.store.model;

import androidx.annotation.IdRes;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

@Table
public class Category extends SugarRecord {

    @Unique
    @Column(name = "IMAGE_RES_ID")
    private int imageResId;

    @Column(name = "NAME")
    private String name;

    public Category() {
    }

    public Category(int imageResId, String name) {
        this.imageResId = imageResId;
        this.name = name;
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
}
