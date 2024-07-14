package com.example.store.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.store.Adapter.ProductAdapter;
import com.example.store.CategoryActivity;
import com.example.store.interfaces.OnItemClickListener;
import com.example.store.R;
import com.example.store.Adapter.CategoryAdapter;
import com.example.store.model.Category;
import com.example.store.model.Product;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewCategory;
    private RecyclerView recyclerViewProduct;
    private List<Category> categoriesData;
    private List<Product> productData;

    private static final int GRID_SPAN_COUNT = 2;
    private static final int PRODUCT_START_PRICE = 100;
    private static final int DATA_SIZE = 20;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Load initial data
        loadInitialData();

        categoriesData = Category.listAll(Category.class);
        productData = Product.listAll(Product.class);

        // Setup RecyclerViews
        recyclerViewCategory = setupRecyclerView(view, R.id.recyclerView_category, LinearLayoutManager.HORIZONTAL, new CategoryAdapter(categoriesData, new OnItemClickListener() {
            @Override
            public void onItemClick(View view,int position) {
                long categoryId = categoriesData.get(position).getId();
                String categoryName = categoriesData.get(position).getName();
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("CATEGORY_ID", categoryId);
                intent.putExtra("CATEGORY_NAME",categoryName);
                startActivity(intent);
            }
        }));
        recyclerViewProduct = setupRecyclerView(view, R.id.recyclerView_product, GRID_SPAN_COUNT, new ProductAdapter(productData, new OnItemClickListener() {
            @Override
            public void onItemClick(View view,int position) {
                String nameProduct = productData.get(position).getName();
//                Intent intent = new Intent(getActivity(), RestaurantsActivity.class);
//                intent.putExtra("ITEM_DATA", nameProduct);
//                startActivity(intent);
                Toast.makeText(getActivity(), "Đã click : " + nameProduct + " " + position, Toast.LENGTH_SHORT).show();
            }
        }));

        // Setup TextViews
        setupTextView(view, R.id.tv_popular, "Popular categories", R.color.grey_500);
        setupTextView(view, R.id.tv_popular_listings, "Popular listings", R.color.grey_500);

        return view;
    }

    private void loadInitialData() {
        Category.deleteAll(Category.class);
        Product.deleteAll(Product.class);
        // Tạo danh sách các đối tượng Category
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(R.drawable.restaurants, "Restaurant"));
        categories.add(new Category(R.drawable.restaurants, "Cafe"));
        categories.add(new Category(R.drawable.restaurants, "Bar"));
        categories.add(new Category(R.drawable.restaurants, "Museum"));

        // Lưu tất cả các đối tượng Category trong một giao dịch
        SugarRecord.saveInTx(categories);

        List<Product> productList = new ArrayList<>();
        for (Category category : categories) {
            for (int i = 1; i <= 5; i++) {
                productList.add(new Product(R.drawable.restaurants, category.getName(), 100 * i, category.getId()));
            }
        }
        SugarRecord.saveInTx(productList);
    }

    private void setupTextView(View parent, int textViewId, String text, int colorId) {
        TextView textView = parent.findViewById(textViewId);
        textView.setText(text);
        textView.setTextColor(ContextCompat.getColor(getContext(), colorId));
    }

    private <T extends RecyclerView.Adapter> RecyclerView setupRecyclerView(View parent, int recyclerViewId, int orientation, T adapter) {
        RecyclerView recyclerView = parent.findViewById(recyclerViewId);
        if (orientation == GRID_SPAN_COUNT) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), GRID_SPAN_COUNT));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), orientation, false));
        }
        recyclerView.setAdapter(adapter);

        return recyclerView;
    }
}
