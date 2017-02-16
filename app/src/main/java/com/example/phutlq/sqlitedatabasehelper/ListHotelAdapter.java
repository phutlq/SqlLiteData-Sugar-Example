package com.example.phutlq.sqlitedatabasehelper;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhuTLQ on 2/16/2017.
 */

public class ListHotelAdapter  extends ArrayAdapter<HotelTable> {
    private final Activity context;
    private List<HotelTable> mListHotel;

    public ListHotelAdapter(Activity context,List<HotelTable> mListHotel) {
        super(context, R.layout.item_hotel, mListHotel);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.mListHotel = mListHotel;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.item_hotel, null,true);

        TextView textName = (TextView) rowView.findViewById(R.id.item_hotel_textview_name);
        TextView textAdress = (TextView) rowView.findViewById(R.id.item_hotel_textview_address);

        textName.setText(mListHotel.get(position).getName());
        textAdress.setText(mListHotel.get(position).getAdress1());
        return rowView;

    };
}