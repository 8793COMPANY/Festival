package com.corporation8793.festival.listener;

import android.view.View;

import com.corporation8793.festival.adapter.ReservationAdapter;

public interface OnItemClickListener {
    public void onItemClick(ReservationAdapter.ViewHolder holder, View view, int position);
}
