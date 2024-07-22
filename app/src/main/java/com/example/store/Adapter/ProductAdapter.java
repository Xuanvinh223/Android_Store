package com.example.store.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
    private static final int TYPE_DASHBOARD = R.integer.TYPE_DASHBOARD;

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
            return new ProductViewHolder(view, listener);
        } else if (this.typeView == TYPE_CATEGORY) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_product, parent, false);
            return new CategoryViewHolder(view, listener);
        } else if (this.typeView == TYPE_DASHBOARD) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_dashboard, parent, false);
            return new DashboardViewHolder(view, listener);
        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_product, parent, false);
        return new ProductViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product product = data.get(position);
        if (holder instanceof ProductViewHolder) {
            ((ProductViewHolder) holder).bind(product);
        } else if (holder instanceof CategoryViewHolder) {
            ((CategoryViewHolder) holder).bind(product);
        } else if (holder instanceof DashboardViewHolder) {
            ((DashboardViewHolder) holder).bind(product);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView textViewProductName;
        TextView textViewProductPrice;
        ImageView imageViewFavorite;
        FrameLayout layout_favorites;

        public ProductViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct_home_product);
            textViewProductName = itemView.findViewById(R.id.textViewProductName_home_product);
            textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice_home_product);
            imageViewFavorite = itemView.findViewById(R.id.imageViewFavorite);
            layout_favorites = itemView.findViewById(R.id.layout_favorites);
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
            textViewProductName.setText(product.getName());
            textViewProductPrice.setText(product.getPrice() + " $");
            imageViewProduct.setImageResource(product.getImageResId());

            layout_favorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Integer currentDrawable = (Integer) imageViewFavorite.getTag();
                        if (currentDrawable != null && currentDrawable == R.drawable.ic_heart) {
                            imageViewFavorite.setImageResource(R.drawable.ic_heart_boder);
                            imageViewFavorite.setTag(R.drawable.ic_heart_boder);
                        } else {
                            imageViewFavorite.setImageResource(R.drawable.ic_heart);
                            imageViewFavorite.setTag(R.drawable.ic_heart);
                        }
                    }
                }
            });
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

    public static class DashboardViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView textViewProductName;
        TextView textViewProductPrice;

        public DashboardViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
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

