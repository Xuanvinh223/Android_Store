package com.example.store;

import android.os.Bundle;

import com.example.store.Adapter.CategoryAdapter;
import com.example.store.interfaces.OnItemClickListener;
import com.example.store.model.Category;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.store.databinding.ActivityCategoryBinding;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private ActivityCategoryBinding binding;
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private List<Category> categories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NotificationHelper notificationHelper = new NotificationHelper(getApplicationContext(), getSupportFragmentManager());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categories = Category.listAll(Category.class);
        categoryAdapter = new CategoryAdapter(categories, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                notificationHelper.showToast(categories.get(position).getName(), Toast.LENGTH_SHORT, R.mipmap.ic_launcher);
            }
        }, R.integer.TYPE_CATEGORY);
        recyclerView.setAdapter(categoryAdapter);
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