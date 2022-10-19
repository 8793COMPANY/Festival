package com.corporation8793.festival.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.corporation8793.festival.mclass.MyList2;
import com.corporation8793.festival.R;

import java.util.ArrayList;

public class ListAdapter2 extends BaseAdapter {
    ArrayList<MyList2> items = new ArrayList<MyList2>();
    Context context;
    int index;

    public ListAdapter2(Context context, int index) {
        this.context = context;
        this.index = index;
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
        View view;

        if(index == 1) {
            view = inflater.inflate(R.layout.listview_item3, parent, false);

            TextView rankText, rankNameText;

            rankText = view.findViewById(R.id.rankText);
            rankNameText = view.findViewById(R.id.rankNameText);

            MyList2 item = items.get(position);
            rankText.setText(item.getMainText());
            rankNameText.setText(item.getSubText());

        } else {
            view = inflater.inflate(R.layout.listview_item2, parent, false);

            TextView mainText, subText;

            mainText = view.findViewById(R.id.mainText);
            subText = view.findViewById(R.id.subText);

            MyList2 item = items.get(position);
            mainText.setText(item.getMainText());
            subText.setText(item.getSubText());
        }

        return view;
    }


    public void addItem(MyList2 item) {
        items.add(item);
    }
}
