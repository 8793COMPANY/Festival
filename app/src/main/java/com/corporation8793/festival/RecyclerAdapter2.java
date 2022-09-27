package com.corporation8793.festival;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.ViewHolder> {
    ArrayList<Festival2> items = new ArrayList<Festival2>();
    Context context;

    public RecyclerAdapter2(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_recyclerview_search, parent, false);

        itemView.getLayoutParams().width = 480;

        return new RecyclerAdapter2.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter2.ViewHolder holder, int position) {
        Festival2 item = items.get(position);
        holder.setItem(item);
    }

    public void addItem(Festival2 item) {
        items.add(item);
    }

    public void setItems(ArrayList<Festival2> items) {
        this.items = items;
    }
    public Festival2 getItem(int position) {
        return items.get(position);
    }
    public void setItem(int position, Festival2 item) {
        items.set(position, item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mainText;
        ImageView searchPageImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mainText = itemView.findViewById(R.id.mainText);
            searchPageImage = itemView.findViewById(R.id.searchPageImage);

        }

        public void setItem(Festival2 item) {
            mainText.setText(item.getName());
            searchPageImage.setImageResource(item.getImage());
        }

    }
}
