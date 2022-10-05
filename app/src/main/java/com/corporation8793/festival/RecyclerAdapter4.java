package com.corporation8793.festival;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter4 extends RecyclerView.Adapter<RecyclerAdapter4.ViewHolder> {
    ArrayList<Booth> items = new ArrayList<Booth>();
    Context context;

    public RecyclerAdapter4(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter4.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_recyclerview_booth, parent, false);

        itemView.getLayoutParams().height = 400;

        return new RecyclerAdapter4.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter4.ViewHolder holder, int position) {
        Booth item = items.get(position);
        holder.setItem(item);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView boothName, boothTime, boothPoint, saveResult;
        Button cameraButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            boothName = itemView.findViewById(R.id.boothName);
            boothTime = itemView.findViewById(R.id.boothTime);
            boothPoint = itemView.findViewById(R.id.boothPoint);
            saveResult = itemView.findViewById(R.id.saveResult);
            cameraButton = itemView.findViewById(R.id.cameraButton);

            cameraButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    capture();
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
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ((Activity)context).startActivityForResult(intent,101);

        }
    }
}
