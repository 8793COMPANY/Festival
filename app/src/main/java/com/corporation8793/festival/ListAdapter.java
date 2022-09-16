package com.corporation8793.festival;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    ArrayList<MyList> items = new ArrayList<MyList>();
    Context context;
    TextView listText;
    ImageView listImage, listEvent;
    //Switch switchButton;

    public ListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public MyList getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listview_item, parent, false);

        //TextView listText;
        //ImageView listImage, listEvent;
        //Switch switchButton;

        listText = view.findViewById(R.id.listText);
        listImage = view.findViewById(R.id.listImage);
        listEvent = view.findViewById(R.id.listEvent);
        //switchButton = view.findViewById(R.id.switchButton);

        MyList item = items.get(position);
        listText.setText(item.getListName());
        listImage.setImageResource(item.getImage1());
        listEvent.setImageResource(item.getImage2());

        return view;
    }

    public void addItem(MyList item)  {
        items.add(item);
    }
}
