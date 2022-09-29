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
/*
public class RecyclerAdapter3 extends RecyclerView.Adapter<RecyclerAdapter3.ViewHolder> implements OnItemClickListener2{
    ArrayList<Festival> items = new ArrayList<Festival>();
    Context context;
    OnItemClickListener2 listener;

    public RecyclerAdapter3(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_recyclerview_mypage, parent, false);

        itemView.getLayoutParams().height = 700;

        return new ViewHolder(itemView, this);
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

    public void setOnItemClicklistener(OnItemClickListener2 listener) {
        this.listener = listener;
    }

    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView rFestivalName, rFestivalInfo;
        ImageView rFestivalImage;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener2 listener) {
            super(itemView);

            rFestivalName = itemView.findViewById(R.id.rFestivalName);
            rFestivalInfo = itemView.findViewById(R.id.rFestivalInfo);
            rFestivalImage = itemView.findViewById(R.id.rFestivalImage);

            rFestivalImage.setClipToOutline(true);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null) {
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });

        }

        public void setItem(Festival item) {
            rFestivalInfo.setText(item.getPeriod());
            rFestivalName.setText(item.getName());
            rFestivalImage.setImageResource(item.getImage());
        }

    }
}*/
