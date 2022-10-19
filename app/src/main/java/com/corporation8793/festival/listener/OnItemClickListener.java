package com.corporation8793.festival.listener;

import android.view.View;

import com.corporation8793.festival.adapter.RecyclerAdapter;

public interface OnItemClickListener {
    public void onItemClick(RecyclerAdapter.ViewHolder holder, View view, int position);
}
