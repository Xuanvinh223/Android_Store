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
import com.example.store.model.Category;
import com.example.store.model.Product;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final List<Category> data;
    private OnItemClickListener listener;
    private final int typeView;
    private static final int TYPE_HOME = R.integer.TYPE_HOME;
    private static final int TYPE_CATEGORY = R.integer.TYPE_CATEGORY;

    public CategoryAdapter(List<Category> data, OnItemClickListener listener, int typeView) {
        this.data = data;
        this.listener = listener;
        this.typeView = typeView;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (this.typeView == TYPE_HOME) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
            return new MyViewHolder(view, listener);
        } else if (this.typeView == TYPE_CATEGORY) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories, parent, false);
            return new MyViewHolder(view, listener);
        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Category category = data.get(position);
        if (holder instanceof CategoryAdapter.MyViewHolder) {
            ((CategoryAdapter.MyViewHolder) holder).bind(category);
        } else if (holder instanceof CategoryAdapter.CategoryViewHolder) {
            ((CategoryAdapter.CategoryViewHolder) holder).bind(category);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView textViewProductName;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);

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

        void bind(Category category) {
            imageViewProduct.setImageResource(category.getImageResId());
            textViewProductName.setText(category.getName());
        }
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView textViewProductName;

        public CategoryViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);

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

        void bind(Category category) {
            imageViewProduct.setImageResource(category.getImageResId());
            textViewProductName.setText(category.getName());
        }
    }

}
