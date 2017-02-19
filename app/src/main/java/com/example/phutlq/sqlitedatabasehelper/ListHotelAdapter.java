package com.example.phutlq.sqlitedatabasehelper;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhuTLQ on 2/16/2017.
 */

public class ListHotelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    List<HotelTable> mListHotel;

    OnItemClickListener clickListener;

    public ListHotelAdapter(Context context, List<HotelTable> mListHotel) {
        this.mContext = context;
        this.mListHotel = mListHotel;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_hotel, parent, false);
        return new HotelHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((HotelHolder) holder).name.setText(mListHotel.get(position).getName());
        ((HotelHolder) holder).adress.setText(mListHotel.get(position).getAdress1());

    }

    @Override
    public int getItemCount() {
        return mListHotel.size();
    }

    class HotelHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, adress;

        public HotelHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.item_hotel_textview_name);
            adress = (TextView) itemView.findViewById(R.id.item_hotel_textview_address);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
