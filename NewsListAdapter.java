package com.example.worldskills;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {
    ArrayList<NewsModel> news;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView labelText, subLabelText, price;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            labelText = itemView.findViewById(R.id.cardLabelText);
            subLabelText = itemView.findViewById(R.id.cardSubLabelText);
            price = itemView.findViewById(R.id.cardPrice);
            image = itemView.findViewById(R.id.imageView);
        }

        public TextView getLabelText() {
            return labelText;
        }

        public void setLabelText(TextView labelText) {
            this.labelText = labelText;
        }

        public TextView getSubLabelText() {
            return subLabelText;
        }

        public void setSubLabelText(TextView subLabelText) {
            this.subLabelText = subLabelText;
        }

        public ImageView getImage() {
            return image;
        }

        public void setImage(ImageView image) {
            this.image = image;
        }

        public TextView getPrice() {
            return price;
        }

        public void setPrice(TextView price) {
            this.price = price;
        }
    }

    public NewsListAdapter(ArrayList<NewsModel> news) {
        this.news = news;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        // Создаем градиент
        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.BOTTOM_TOP,
                new int[] {R.color.gradientEnd, R.color.gradientStart}
    );
        view.setBackground(gradientDrawable);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.labelText.setText(news.get(position).Name);
        holder.subLabelText.setText(news.get(position).Description);
        holder.price.setText(String.valueOf(news.get(position).Price));
        holder.image.setImageDrawable(news.get(position).Image);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }
}
