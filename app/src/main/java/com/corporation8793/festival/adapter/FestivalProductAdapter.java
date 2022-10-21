package com.corporation8793.festival.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.corporation8793.festival.mclass.Product;
import com.corporation8793.festival.R;

import java.util.ArrayList;

public class FestivalProductAdapter extends RecyclerView.Adapter<FestivalProductAdapter.ViewHolder> {
    ArrayList<Product> items = new ArrayList<Product>();
    Context context;

    public interface OnItemClickListener {
        void onScanClick(View view, int position);
    }
    private OnItemClickListener mListener = null;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public FestivalProductAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_recyclerview_product, parent, false);

        itemView.getLayoutParams().height = 200;

        return new FestivalProductAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product item = items.get(position);
        holder.setItem(item);
    }

    public void addItem(Product item) {
        items.add(item);
    }

    public Product getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPoint;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productName);
            productPoint = itemView.findViewById(R.id.productPoint);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        if(mListener != null) {
                            mListener.onScanClick(v, position);
                        }
                    }
                }
            });
        }

        public void setItem(Product item) {
            productName.setText(item.getProductName());
            productPoint.setText(item.getProductPoint());
        }
    }
}
