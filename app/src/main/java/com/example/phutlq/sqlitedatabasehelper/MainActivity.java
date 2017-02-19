package com.example.phutlq.sqlitedatabasehelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListViewHotel;

    private Button mButtonAdd;

    private ListHotelAdapter mListHotelAdapter;

    private List<HotelTable> mListHotel;

    private HotelTable mHotelTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewHotel = (ListView) findViewById(R.id.activity_main_list_hotel);
        mButtonAdd = (Button) findViewById(R.id.activity_main_buton_add);
        mListHotel = new ArrayList<HotelTable>();
        long count = HotelTable.count(HotelTable.class);
        if(count>0)
        {
            mListHotelAdapter = new ListHotelAdapter(this,mListHotel);
            mListHotel = HotelTable.listAll(HotelTable.class);
            mListViewHotel.setAdapter(mListHotelAdapter);
            mListHotelAdapter.notifyDataSetChanged();
            mListViewHotel.setOnItemClickListener((parent, view, position, id) -> {
                DialogHelper.callDialog(MainActivity.this,Constant.TYPE_UPDATE,mListHotel.get(position));
                reloadAllData();

            });
        }
        mButtonAdd.setOnClickListener(v -> {
            DialogHelper.callDialog(MainActivity.this,Constant.TYPE_ADD,mHotelTable);
            reloadAllData();
        });
    }

    private void reloadAllData(){
        mListHotel.clear();
        mListHotel = HotelTable.listAll(HotelTable.class);
        mListHotelAdapter = new ListHotelAdapter(this,mListHotel);
        mListViewHotel.setAdapter(mListHotelAdapter);
        mListHotelAdapter.notifyDataSetChanged();
    }

}
