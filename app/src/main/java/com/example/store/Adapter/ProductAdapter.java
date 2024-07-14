package com.example.store.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.store.interfaces.OnItemClickListener;
import com.example.store.R;
import com.example.store.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Product> data;
    private final OnItemClickListener listener;
    private final int typeView;
    private static final int TYPE_HOME = R.integer.TYPE_HOME;
    private static final int TYPE_CATEGORY = R.integer.TYPE_CATEGORY;

    public ProductAdapter(List<Product> data, OnItemClickListener listener, int typeView) {
        this.data = data;
        this.listener = listener;
        this.typeView = typeView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (this.typeView == TYPE_HOME) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_product, parent, false);
            return new HomeViewHolder(view, listener);
        } else if (this.typeView == TYPE_CATEGORY) { // TYPE_CATEGORY
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_product, parent, false);
            return new CategoryViewHolder(view, listener);
        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_product, parent, false);
        return new HomeViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product product = data.get(position);
        if (holder instanceof HomeViewHolder) {
            ((HomeViewHolder) holder).bind(product);
        } else if (holder instanceof CategoryViewHolder) {
            ((CategoryViewHolder) holder).bind(product);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView textViewProductName;
        TextView textViewProductPrice;

        public HomeViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(v, position);
                        }
                    }
                }
            });
        }

        void bind(Product product) {
            imageViewProduct.setImageResource(product.getImageResId());
            textViewProductName.setText(product.getName());
            textViewProductPrice.setText("$$ " + product.getPrice());
        }
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView textViewProductName;
        TextView textViewProductPrice;

        public CategoryViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(v, position);
                        }
                    }
                }
            });
        }

        void bind(Product product) {
            imageViewProduct.setImageResource(product.getImageResId());
            textViewProductName.setText(product.getName());
            textViewProductPrice.setText(product.getPrice() + "$");
        }
    }
}

