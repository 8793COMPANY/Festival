package com.corporation8793.festival.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.corporation8793.festival.mclass.RankList;
import com.corporation8793.festival.R;

import java.util.ArrayList;

public class RankListAdapter extends BaseAdapter {
    ArrayList<RankList> items = new ArrayList<RankList>();
    Context context;

    public RankListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public RankList getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_listview_rank, parent, false);

        TextView rankText, rankNameText;

        rankText = view.findViewById(R.id.rankText);
        rankNameText = view.findViewById(R.id.rankNameText);

        RankList item = items.get(position);
        rankText.setText(item.getMainText());
        rankNameText.setText(item.getSubText());

        return view;
    }


    public void addItem(RankList item) {
        items.add(item);
    }
}
