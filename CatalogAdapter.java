package com.example.worldskills;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder> {
    ArrayList<CatalogItem> catalog = new ArrayList<>();

    public CatalogAdapter(ArrayList<CatalogItem> catalog) {
        this.catalog = catalog;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView catalogItemLabel, catalogItemPrice, catalogItemTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catalogItemLabel = itemView.findViewById(R.id.catalogItemLabel);
            catalogItemPrice = itemView.findViewById(R.id.catalogItemPrice);
            catalogItemTime = itemView.findViewById(R.id.catalogItemTime);
        }
    }

    @NonNull
    @Override
    public CatalogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogAdapter.ViewHolder holder, int position) {
        if(catalog.get(position).Name.length() > 28) {
            String text = catalog.get(position).Name;
            holder.catalogItemLabel.setText(text.substring(0, 20) + "...");
        }
        else {
            holder.catalogItemLabel.setText(catalog.get(position).Name);
        }
        holder.catalogItemTime.setText(catalog.get(position).TimeResult);
        holder.catalogItemPrice.setText(String.valueOf(catalog.get(position).Price) + "Р");
    }

    @Override
    public int getItemCount() {
        return catalog.size();
    }
}
