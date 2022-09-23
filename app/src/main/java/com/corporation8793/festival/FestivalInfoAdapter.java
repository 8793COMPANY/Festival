package com.corporation8793.festival;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FestivalInfoAdapter extends RecyclerView.Adapter<FestivalInfoAdapter.MyViewHolder> {

    List<FestivalInfo> festivalInfoList;
    Context context;
    AppDatabase2 database2;

    public  FestivalInfoAdapter(Context context/*, List<FestivalInfo> festivalInfoList*/) {
        this.context = context;
        //this.festivalInfoList = festivalInfoList;
        //notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_recyclerview_main, parent, false);

        view.getLayoutParams().height = 520;

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final FestivalInfo data = festivalInfoList.get(position);
        database2 = AppDatabase2.getDBInstance(context);

        holder.festivalNameText.setText(festivalInfoList.get(position).festivalName);
        holder.festivalPeriodText.setText(festivalInfoList.get(position).festivalStart + "~" + festivalInfoList.get(position).festivalEnd);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).fragmentChange(FestivalInfoFragment.newInstance());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.festivalInfoList.size();
    }

    //리스트 저장
    public void setFestivalInfoList(List<FestivalInfo> festivalInfoList) {
        this.festivalInfoList = festivalInfoList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView festivalNameText, festivalPeriodText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            festivalNameText = itemView.findViewById(R.id.festivalName);
            festivalPeriodText = itemView.findViewById(R.id.periodText);

        }
    }

}
