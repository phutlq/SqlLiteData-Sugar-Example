package com.example.phutlq.sqlitedatabasehelper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddHotelActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private FloatingActionButton mButtonDone;

    private EditText mEditTextName;

    private EditText mEditTextAdress;

    private String mName, mAdress;

    private boolean mEditting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);

        mToolbar = (Toolbar) findViewById(R.id.activity_add_hotel_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_clear_24dp);

        getSupportActionBar().setTitle("Add new hotel");

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mEditTextName = (EditText) findViewById(R.id.activity_add_hotel_name);
        mEditTextAdress = (EditText) findViewById(R.id.activity_add_hotel_adress);

        mButtonDone = (FloatingActionButton) findViewById(R.id.addnote_fab);


        mEditting = getIntent().getBooleanExtra("isEditing", false);
        if (mEditting) {
            mName = getIntent().getStringExtra("name");
            mAdress = getIntent().getStringExtra("adress");

            mEditTextName.setText(mName);
            mEditTextAdress.setText(mAdress);

        }


        mButtonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newName = mEditTextName.getText().toString();
                String newAdress = mEditTextAdress.getText().toString();

                if (!mEditting) {
                    Log.d("Hotel", "saving");
                    addNewHotel();
                } else {
                    Log.d("Hotel", "updating");
                   updateHotel(newName,newAdress);
                }
                finish();
            }
        });
    }

    public void addNewHotel() {
        if (mEditTextName.getText().toString().length() == 0|| mEditTextAdress.getText().toString().length() == 0) {
            Toast.makeText(AddHotelActivity.this, "Please fill in all feild", Toast.LENGTH_SHORT).show();
            return;
        } else {
            HotelTable hotelTableTable = new HotelTable(mEditTextName.getText().toString(), mEditTextAdress.getText().toString());
            hotelTableTable.save();
            return;
        }
    }

    public void updateHotel(String newName,String newAdress) {

        if (mEditTextName.getText().toString().length()==0 || mEditTextAdress.getText().toString().length()==0) {
            Toast.makeText(AddHotelActivity.this, "Please fill in all field", Toast.LENGTH_SHORT).show();
            return;
        } else if (mName.equals(newName) && mAdress.equals(newAdress)) {
            Toast.makeText(AddHotelActivity.this, "No field changed", Toast.LENGTH_SHORT).show();
            return;
        } else {
            List<HotelTable> listHotel = HotelTable.find(HotelTable.class, "name = ?", mName);
            if (listHotel.size() > 0) {

                HotelTable hotelTable = listHotel.get(0);
                Log.d("got hotel", "hotel: " + hotelTable.name);
                hotelTable.name = newName;
                hotelTable.adress1 = newAdress;
                hotelTable.save();
            }
            return;
        }
    }
}
