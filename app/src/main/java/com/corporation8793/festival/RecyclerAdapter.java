package com.corporation8793.festival;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<Festival> items = new ArrayList<Festival>();
    Context context;
    DisplayMetrics displayMetrics;

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_recyclerview_main, parent, false);

        //displayMetrics = context.getResources().getDisplayMetrics();
        //int width = displayMetrics.widthPixels;
        //int height = displayMetrics.heightPixels * (339/1031);
        //ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width,height);
        //.LayoutParams layoutParams = new ViewGroup.LayoutParams(itemView.getWidth(),height);
        //itemView.setLayoutParams(layoutParams);
        //itemView.getLayoutParams().height = height;

        itemView.getLayoutParams().height = 520;

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Festival item = items.get(position);
        holder.setItem(item);
    }

    public void addItem(Festival item) {
        items.add(item);
    }

    public void setItems(ArrayList<Festival> items) {
        this.items = items;
    }

    public Festival getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Festival item) {
        items.set(position, item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView periodText, festivalName;
        ImageView festivalImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            periodText = itemView.findViewById(R.id.periodText);
            festivalName = itemView.findViewById(R.id.festivalName);
            festivalImage = itemView.findViewById(R.id.festivalImage);
        }

        public void setItem(Festival item) {
            periodText.setText(item.getPeriod());
            festivalName.setText(item.getName());
            festivalImage.setImageResource(item.getImage());
        }
    }
}