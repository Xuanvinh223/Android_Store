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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private final List<Product> data;
    private OnItemClickListener listener;

    public ProductAdapter(List<Product> data,OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new MyViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = data.get(position);
        holder.imageViewProduct.setImageResource(product.getImageResId());
        holder.textViewProductName.setText(product.getName());
        holder.textViewProductPrice.setText( "$$ " + product.getPrice());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView textViewProductName;
        TextView textViewProductPrice;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
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
                            listener.onItemClick(v,position);
                        }
                    }
                }
            });
        }
    }
}
