package com.example.store;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.store.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle(R.string.Profile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayout layoutMyListings = findViewById(R.id.layout_my_listings);
        LinearLayout layoutFavorites = findViewById(R.id.layout_favorites);
        LinearLayout layoutProfileDetails = findViewById(R.id.layout_profile_details);
        LinearLayout layoutSettings = findViewById(R.id.layout_settings);
        LinearLayout layoutContactUs = findViewById(R.id.layout_contact_us);
        TextView textLogout = findViewById(R.id.text_logout);

        layoutMyListings.setOnClickListener(v -> {
            // Xử lý khi nhấn vào "My Listings"
            Toast.makeText(ProfileActivity.this, "My Listings clicked", Toast.LENGTH_SHORT).show();
        });

        layoutFavorites.setOnClickListener(v -> {
            // Xử lý khi nhấn vào "Favorites"
            Toast.makeText(ProfileActivity.this, "Favorites clicked", Toast.LENGTH_SHORT).show();
        });

        layoutProfileDetails.setOnClickListener(v -> {
            // Xử lý khi nhấn vào "Profile Details"
            Toast.makeText(ProfileActivity.this, "Profile Details clicked", Toast.LENGTH_SHORT).show();
        });

        layoutSettings.setOnClickListener(v -> {
            // Xử lý khi nhấn vào "Settings"
            Toast.makeText(ProfileActivity.this, "Settings clicked", Toast.LENGTH_SHORT).show();
        });

        layoutContactUs.setOnClickListener(v -> {
            // Xử lý khi nhấn vào "Contact Us"
            Toast.makeText(ProfileActivity.this, "Contact Us clicked", Toast.LENGTH_SHORT).show();
        });

        textLogout.setOnClickListener(v -> {
            // Xử lý khi nhấn vào "Logout"
            Toast.makeText(ProfileActivity.this, "Logout clicked", Toast.LENGTH_SHORT).show();
        });


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