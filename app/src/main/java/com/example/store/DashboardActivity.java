package com.example.store;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.store.Adapter.ProductAdapter;
import com.example.store.interfaces.OnItemClickListener;
import com.example.store.model.Product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.store.databinding.ActivityDashboardBinding;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;
    private List<Product> products;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NotificationHelper notificationHelper = new NotificationHelper(this, getSupportFragmentManager());

        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("Dashboard");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_dashboard);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        products = Product.listAll(Product.class);
        productAdapter = new ProductAdapter(products, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                String[] options = {"APPROVE LISTING", "REMOVE LISTING", "CANCEL"};
                showAlertDialog("Phe duyet san pham : " + products.get(position).getName(), options, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            notificationHelper.showToast("1", Toast.LENGTH_SHORT, R.mipmap.ic_launcher);
                            break;
                        case 1:
                            // Handle Remove Listing action
                            notificationHelper.showToast("2", Toast.LENGTH_SHORT, R.mipmap.ic_launcher);
                            break;
                        case 2:
                            // Handle Cancel action
                            dialog.dismiss();
                            break;
                    }
                });

            }
        }, R.integer.TYPE_DASHBOARD);
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

    private void showAlertDialog(String title, String[] options, DialogInterface.OnClickListener optionClickListener) {
        new NotificationHelper(this, getSupportFragmentManager()).showAlertDialog(title, options, optionClickListener);
    }
}