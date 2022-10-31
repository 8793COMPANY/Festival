package com.corporation8793.festival.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.corporation8793.festival.room.AppDatabase;
import com.corporation8793.festival.listener.OnItemClickListener;
import com.corporation8793.festival.R;
import com.corporation8793.festival.room.Reservation;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> implements OnItemClickListener {

    List<Reservation> reservationList;
    Context context;
    ImageView rFestivalImage;
    int[] image;

    OnItemClickListener listener;
    AppDatabase db;

    public ReservationAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_recyclerview_reservation, parent, false);

        rFestivalImage = view.findViewById(R.id.rFestivalImage);

        image = new int[] {R.drawable.mypage_festival_image1, R.drawable.mypage_festival_image2};
        int imageId = (int)(Math.random() * image.length);

        rFestivalImage.setClipToOutline(true);
        rFestivalImage.setImageResource(image[imageId]);

        view.getLayoutParams().height = 750;

        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.rFestivalName.setText(reservationList.get(position).rFestival);
        holder.rFestivalInfo.setText(reservationList.get(position).rDate + " | " + reservationList.get(position).rNum + "명");

        holder.rDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reservation reservation = reservationList.get(holder.getAdapterPosition());

                db = AppDatabase.getDBInstance(context);
                db.reservationDao().deleteReservation(reservation);

                int position = holder.getAdapterPosition();
                reservationList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, reservationList.size());

                SharedPreferences booth = context.getSharedPreferences(String.valueOf(reservation.rid), Activity.MODE_PRIVATE);
                SharedPreferences.Editor boothEdit = booth.edit();
                boothEdit.clear();
                boothEdit.commit();
            }
        });
    }

    //리스트 위치 가져오기
    public Reservation getItem(int position) {
        return reservationList.get(position);
    }

    @Override
    public int getItemCount() {
        return this.reservationList.size();
    }

    //리스트 저장
    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }
    //아이템 삭제
    public void removeItem(int position) {
        reservationList.remove(position);
        notifyItemRemoved(position);
        //갱신
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView rFestivalName, rFestivalInfo;
        Button rDeleteButton;
        ImageView rOffImage;

        public ViewHolder(@NonNull View view, final OnItemClickListener listener) {
            super(view);

            rFestivalName = view.findViewById(R.id.rFestivalName);
            rFestivalInfo = view.findViewById(R.id.rFestivalInfo);
            rDeleteButton = view.findViewById(R.id.rDeleteButton);
            rOffImage = view.findViewById(R.id.rOffImage);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null) {
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });
        }
    }
}
