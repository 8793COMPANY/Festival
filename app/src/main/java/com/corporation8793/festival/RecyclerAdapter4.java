package com.corporation8793.festival;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter4 extends RecyclerView.Adapter<RecyclerAdapter4.ViewHolder> implements OnItemClickListener2 {
    ArrayList<Booth> items = new ArrayList<Booth>();
    Context context;
    OnItemClickListener2 listener;

    interface OnItemClickListener{
        void onScanClick(View view, int position);
    }
    private OnItemClickListener mListener = null;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public RecyclerAdapter4(Context context/*, ArrayList<Booth> list*/) {
        this.context = context;
        //this.items = list;
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
                        //Toast.makeText(context, "실행되었습니다.", Toast.LENGTH_SHORT).show();
                        //saveResult.setText(item.getSaveResult());
                        items.get(position).saveResult = "적립완료";
                        holder.saveResult.setText("적립완료");
                        //Toast.makeText(context, items.get(position).saveResult, Toast.LENGTH_SHORT).show();
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

        public ViewHolder(@NonNull View itemView, final OnItemClickListener2 listener) {
            super(itemView);

            boothName = itemView.findViewById(R.id.boothName);
            boothTime = itemView.findViewById(R.id.boothTime);
            boothPoint = itemView.findViewById(R.id.boothPoint);
            saveResult = itemView.findViewById(R.id.saveResult);
            cameraButton = itemView.findViewById(R.id.cameraButton);

            cameraButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //capture();
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

        public void capture(){
            //Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //((Activity)context).startActivityForResult(intent,101);
            Intent intent = new Intent(context, ScanQrActivity.class);
            context.startActivity(intent);
        }
    }
}
