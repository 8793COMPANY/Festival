package com.corporation8793.festival;

import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements OnItemClickListener {
    ArrayList<Festival> items = new ArrayList<Festival>();
    Context context;
    OnItemClickListener listener;
    //DisplayMetrics displayMetrics;

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


    public void setOnItemClicklistener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView periodText, festivalName;
        ImageView festivalImage;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            periodText = itemView.findViewById(R.id.periodText);
            festivalName = itemView.findViewById(R.id.festivalName);
            festivalImage = itemView.findViewById(R.id.festivalImage);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null) {
                        //listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });

            //itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(context, FestivalInfoFragment.class);
                        context.startActivity(intent);
                        Toast.makeText(context,"ddddddddddd",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        public void setItem(Festival item) {
            periodText.setText(item.getPeriod());
            festivalName.setText(item.getName());
            festivalImage.setBackgroundResource(item.getImage());
        }
    }
}
