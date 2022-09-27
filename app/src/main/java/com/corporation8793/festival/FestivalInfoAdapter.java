package com.corporation8793.festival;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FestivalInfoAdapter extends RecyclerView.Adapter<FestivalInfoAdapter.MyViewHolder> {
    List<FestivalInfo> festivalInfoList;
    Context context;
    AppDatabase2 database2;
    int page;
    int[] image;
    ImageView festivalImage;

    public  FestivalInfoAdapter(Context context, int page) {
        this.context = context;
        this.page = page;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_recyclerview_main, parent, false);

        festivalImage = view.findViewById(R.id.festivalImage);

        image = new int[] {R.drawable.festival_image_1, R.drawable.festival_image_2, R.drawable.festival_image_3};
        int imageId = (int)(Math.random() * image.length);

        festivalImage.setBackgroundResource(image[imageId]);

        view.getLayoutParams().height = 520;

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final FestivalInfo data = festivalInfoList.get(position);
        database2 = AppDatabase2.getDBInstance(context);

        holder.festivalNameText.setText(data.festivalName);
        holder.festivalPeriodText.setText(data.festivalStart + "~" + data.festivalEnd);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((MainActivity)context).fragmentChange(FestivalInfoFragment.newInstance());
                FragmentTransaction transaction = ((MainActivity)context).getSupportFragmentManager().beginTransaction();
                FestivalInfoFragment festivalInfoFragment = new FestivalInfoFragment();

                Bundle bundle = new Bundle();

                if(page == 1) {
                    bundle.putString("이동", "메인");
                } else if(page == 2) {
                    bundle.putString("이동", "이벤트");
                } else if(page == 3) {
                    bundle.putString("이동", "검색");
                }

                bundle.putString("이름", data.festivalName);
                bundle.putString("기간", data.festivalStart + "~" + data.festivalEnd);
                bundle.putString("내용", data.festivalCo);
                bundle.putString("주관", data.festivalMnnst);
                bundle.putString("주최", data.festivalAuspcInstt);
                bundle.putString("위치", data.festivalLocation);
                bundle.putString("도로명", data.festivalRdnmadr);
                bundle.putString("지번", data.festivalLnmadr);
                bundle.putString("위도", data.festivalLatitude);
                bundle.putString("경도", data.festivalLongitude);

                festivalInfoFragment.setArguments(bundle);
                transaction.replace(R.id.containers,festivalInfoFragment);
                transaction.addToBackStack(null);
                transaction.commit();
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
        notifyDataSetChanged();
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
