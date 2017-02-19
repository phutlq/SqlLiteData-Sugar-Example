package com.example.phutlq.sqlitedatabasehelper;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mListViewHotel;

    private FloatingActionButton mButtonAdd;
    private ListHotelAdapter mListHotelAdapter;

    private List<HotelTable> mListHotel;

    private HotelTable mHotelTable;

    long initialCount;

    int modifyPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewHotel = (RecyclerView) findViewById(R.id.activity_main_list_hotel);
        mButtonAdd = (FloatingActionButton) findViewById(R.id.activity_main_fab);
        mListHotel = new ArrayList<HotelTable>();
        initialCount = HotelTable.count(HotelTable.class);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mListViewHotel.setLayoutManager(mLayoutManager);
//        mListViewHotel.setHasFixedSize(true);
        mListViewHotel.setItemAnimator(new DefaultItemAnimator());

        if (savedInstanceState != null)
            modifyPos = savedInstanceState.getInt("modify");


        if (initialCount > 0) {
            firstLoad();
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_add_24dp);
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(drawable, Color.WHITE);
            DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);

            mButtonAdd.setImageDrawable(drawable);

        }

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, AddHotelActivity.class);
                startActivity(i);

            }
        });

        // Handling swipe to delete
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //Remove swiped item from list and notify the RecyclerView

                final int position = viewHolder.getAdapterPosition();
                final HotelTable hotelTable = mListHotel.get(viewHolder.getAdapterPosition());
                mListHotel.remove(viewHolder.getAdapterPosition());
                mListHotelAdapter.notifyItemRemoved(position);

                hotelTable.delete();
                initialCount -= 1;

                Snackbar.make(mListViewHotel, "Hotel deleted", Snackbar.LENGTH_SHORT)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                hotelTable.save();
                                mListHotel.add(position, hotelTable);
                                mListHotelAdapter.notifyItemInserted(position);
                                initialCount += 1;

                            }
                        })
                        .show();
            }

        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mListViewHotel);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("modify", modifyPos);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        modifyPos = savedInstanceState.getInt("modify");
    }

    @Override
    protected void onResume() {
        super.onResume();
        final long newCount = HotelTable.count(HotelTable.class);

        if (newCount > initialCount) {
            // A note is added
            Log.d("Main", "Adding new note");

            // Just load the last added note (new)
            HotelTable hotelTable = HotelTable.last(HotelTable.class);
            mListHotel.add(hotelTable);
            if (mListHotel.size() - 1 > 0) {
                mListHotelAdapter.notifyItemInserted((int) newCount);
            } else {
                firstLoad();
            }
            initialCount = newCount;
        }
        if (modifyPos != -1) {
            mListHotel.set(modifyPos, HotelTable.listAll(HotelTable.class).get(modifyPos));
            mListHotelAdapter.notifyItemChanged(modifyPos);
        }
    }

    public void firstLoad() {
        mListHotel = HotelTable.listAll(HotelTable.class);
        mListHotelAdapter = new ListHotelAdapter(this, mListHotel);
        mListViewHotel.setAdapter(mListHotelAdapter);
        mListHotelAdapter.notifyDataSetChanged();

        mListHotelAdapter.SetOnItemClickListener(new ListHotelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("Main", "click");

                Intent i = new Intent(MainActivity.this, AddHotelActivity.class);
                i.putExtra("isEditing", true);
                i.putExtra("name", mListHotel.get(position).name);
                i.putExtra("adress", mListHotel.get(position).adress1);

                modifyPos = position;

                startActivity(i);
            }
        });

        if (mListHotel.isEmpty())
            Snackbar.make(mListViewHotel, "No hotel added.", Snackbar.LENGTH_LONG).show();

    }
}
