package com.example.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.store.Adapter.ProductAdapter;
import com.example.store.interfaces.OnItemClickListener;
import com.example.store.model.Product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.store.databinding.ActivityProductCategoryBinding;

import java.util.List;

public class ProductCategoryActivity extends AppCompatActivity {

    private ActivityProductCategoryBinding binding;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private Intent intent;
    private  NotificationHelper notificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notificationHelper = new NotificationHelper(getApplicationContext(), getSupportFragmentManager());
        intent = getIntent();
        long categoryId = intent.getLongExtra("CATEGORY_ID",0);
        String categoryName = intent.getStringExtra("CATEGORY_NAME");

        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle(categoryName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView_restaurants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = Product.find(Product.class, "CATEGORY_ID = ?", String.valueOf(categoryId));
        productAdapter = new ProductAdapter(productList, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                notificationHelper.showToast(productList.get(position).getName(), Toast.LENGTH_SHORT,R.mipmap.ic_launcher);
            }
        }, R.integer.TYPE_CATEGORY);
        recyclerView.setAdapter(productAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}