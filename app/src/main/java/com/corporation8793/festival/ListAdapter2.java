package com.corporation8793.festival;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter2 extends BaseAdapter {
    ArrayList<MyList2> items = new ArrayList<MyList2>();
    Context context;

    public ListAdapter2(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public MyList2 getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.listview_item2, parent, false);

        TextView mainText, subText;

        mainText = view.findViewById(R.id.mainText);
        subText = view.findViewById(R.id.subText);

        MyList2 item = items.get(position);
        mainText.setText(item.getMainText());
        subText.setText(item.getSubText());

        return view;
    }

    public void addItem(MyList2 item) {
        items.add(item);
    }
}
