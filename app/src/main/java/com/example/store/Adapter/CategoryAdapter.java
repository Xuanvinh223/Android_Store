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

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    public final List<Category> data;
    private OnItemClickListener listener;

    public CategoryAdapter(List<Category> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category Category = data.get(position);
        holder.imageViewProduct.setImageResource(Category.getImageResId());
        holder.textViewProductName.setText(Category.getName());
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
                            listener.onItemClick(v,position);
                        }
                    }
                }
            });
        }
    }
}
