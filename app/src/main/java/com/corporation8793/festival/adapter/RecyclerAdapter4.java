package com.corporation8793.festival.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.corporation8793.festival.mclass.Booth;
import com.corporation8793.festival.R;
import com.corporation8793.festival.listener.OnItemClickListener2;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter4 extends RecyclerView.Adapter<RecyclerAdapter4.ViewHolder> implements OnItemClickListener2 {
    ArrayList<Booth> items = new ArrayList<Booth>();
    Context context;
    OnItemClickListener2 listener;

    public interface OnItemClickListener {
        void onScanClick(View view, int position);
    }
    private OnItemClickListener mListener = null;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public RecyclerAdapter4(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter4.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_recyclerview_booth, parent, false);

        itemView.getLayoutParams().height = 400;

        return new RecyclerAdapter4.ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter4.ViewHolder holder, int position) {
        Booth item = items.get(position);
        holder.setItem(item);

        if(item.getSaveResult().equals("적립완료")) {
            holder.setOff(item);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter4.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if(payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            for(Object payload : payloads) {
                if (payload instanceof String) {
                    String type = (String) payload;
                    if (TextUtils.equals(type, "payload")) {
                        items.get(position).saveResult = "적립완료";
                        holder.saveResult.setText("적립완료");

                        holder.boothImage.setImageResource(R.drawable.rounded_corner3);
                        holder.cameraButton.setText("이용 완료");
                        holder.cameraButton.setBackgroundResource(R.drawable.custom_button_camera_off);
                        holder.boothName.setTextColor(Color.parseColor("#cccccc"));
                        holder.boothTime.setTextColor(Color.parseColor("#cccccc"));
                        holder.boothPoint.setTextColor(Color.parseColor("#cccccc"));
                        holder.saveResult.setTextColor(Color.parseColor("#cccccc"));
                    }
                }
            }
        }
    }

    public void addItem(Booth item) {
        items.add(item);
    }

    public void setItems(ArrayList<Booth> items) {
        this.items = items;
    }
    public Booth getItem(int position) {
        return items.get(position);
    }
    public void setItem(int position, Booth item) {
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView boothName, boothTime, boothPoint, saveResult;
        Button cameraButton;
        ImageView boothImage;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener2 listener) {
            super(itemView);

            boothName = itemView.findViewById(R.id.boothName);
            boothTime = itemView.findViewById(R.id.boothTime);
            boothPoint = itemView.findViewById(R.id.boothPoint);
            saveResult = itemView.findViewById(R.id.saveResult);
            cameraButton = itemView.findViewById(R.id.cameraButton);
            boothImage = itemView.findViewById(R.id.boothImage);

            cameraButton.setOnClickListener(new View.OnClickListener() {
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

        public void setItem(Booth item) {
            boothName.setText(item.getBoothName());
            boothTime.setText(item.getBoothTime());
            boothPoint.setText(item.getBoothPoint());
            saveResult.setText(item.getSaveResult());
        }

        public void setOff(Booth item) {
            boothImage.setImageResource(R.drawable.rounded_corner3);
            cameraButton.setText("이용 완료");
            cameraButton.setBackgroundResource(R.drawable.custom_button_camera_off);
            boothName.setTextColor(Color.parseColor("#cccccc"));
            boothTime.setTextColor(Color.parseColor("#cccccc"));
            boothPoint.setTextColor(Color.parseColor("#cccccc"));
            saveResult.setTextColor(Color.parseColor("#cccccc"));
        }
    }
}
