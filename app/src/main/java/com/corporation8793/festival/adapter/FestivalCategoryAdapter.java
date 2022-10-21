package com.corporation8793.festival.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.corporation8793.festival.mclass.Festival;
import com.corporation8793.festival.R;

import java.util.ArrayList;

public class FestivalCategoryAdapter extends RecyclerView.Adapter<FestivalCategoryAdapter.ViewHolder> {
    ArrayList<Festival> items = new ArrayList<Festival>();
    Context context;

    public interface OnCategoryClickListener{
        void onCategoryClick(View view, int position);
    }
    OnCategoryClickListener mListener = null;
    public void setOnCategoryClickListener(OnCategoryClickListener listener) {
        this.mListener = listener;
    }

    public FestivalCategoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FestivalCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_recyclerview_search, parent, false);

        itemView.getLayoutParams().width = 450;

        return new FestivalCategoryAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FestivalCategoryAdapter.ViewHolder holder, int position) {
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mainText;
        ImageView searchPageImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mainText = itemView.findViewById(R.id.mainText);
            searchPageImage = itemView.findViewById(R.id.searchPageImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        if(mListener != null) {
                            mListener.onCategoryClick(v, position);
                        }
                    }
                }
            });
        }

        public void setItem(Festival item) {
            searchPageImage.setImageResource(item.getImage());
        }

    }
}
