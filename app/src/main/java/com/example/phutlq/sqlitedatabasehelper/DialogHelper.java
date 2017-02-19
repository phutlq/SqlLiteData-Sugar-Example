package com.example.phutlq.sqlitedatabasehelper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by PhuTLQ on 2/16/2017.
 */

public class DialogHelper {

    public static void callDialog(final Context context, final int type, final HotelTable hotelTable) {

        AlertDialog optionDialog = new AlertDialog.Builder(context).create();
        optionDialog.setCancelable(true);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialoglayout = inflater.inflate(R.layout.dialog_add_hotel, null);
        TextView textDescription = (TextView) dialoglayout.findViewById(R.id.dialog_text_description);
        final EditText editTextHotelName = (EditText) dialoglayout.findViewById(R.id.dialog_edittext_hotel_name);
        final EditText editTextHotelAdress = (EditText) dialoglayout.findViewById(R.id.dialog_edittext_hotel_address);
        Button buttonUpdate = (Button) dialoglayout.findViewById(R.id.dialog_add_buton_update);
        Button buttonDelete = (Button) dialoglayout.findViewById(R.id.dialog_add_buton_delete);
        Button buttonCancel = (Button) dialoglayout.findViewById(R.id.dialog_add_buton_cancel);

        if (type == Constant.TYPE_ADD) {
            optionDialog.setTitle(Constant.ADD_HOTEL_TITLE);
            textDescription.setText(Constant.ADD_HOTEL_DESCRIPTION);
            editTextHotelName.setHint(Constant.ADD_HOTEL_NAME_HINT);
            editTextHotelAdress.setHint(Constant.ADD_HOTEL_ADDRESS_HINT);
            buttonUpdate.setVisibility(View.GONE);
            buttonDelete.setText("Add a new hotel");
            buttonDelete.setOnClickListener(v -> {
                if (editTextHotelName.getText().toString().length() == 0|| editTextHotelAdress.getText().toString().length() == 0) {
                    Toast.makeText(context, "Please fill in all feild", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    HotelTable hotelTableTable = new HotelTable(editTextHotelName.getText().toString(), editTextHotelAdress.getText().toString());
                    hotelTableTable.save();
                    editTextHotelName.setText(null);
                    editTextHotelAdress.setText(null);
                    optionDialog.dismiss();
                    Toast.makeText(context, "Hotel was added to list", Toast.LENGTH_SHORT).show();
                    return;
                }
            });
            buttonCancel.setOnClickListener(v -> optionDialog.dismiss());
        } else {
            optionDialog.setTitle(Constant.UPDATE_HOTEL_TITLE);
            textDescription.setText(Constant.UPDATE_HOTEL_DESCRIPTION);
            editTextHotelName.setText(hotelTable.getName());
            editTextHotelAdress.setText(hotelTable.getAdress1());
            buttonUpdate.setOnClickListener(v -> {
                if (type == Constant.TYPE_UPDATE) {
                    if (editTextHotelName.getText().toString().length()==0 || editTextHotelAdress.getText().toString().length()==0) {
                        Toast.makeText(context, "Please fill in all field", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (editTextHotelName.getText().toString().equals(hotelTable.getName()) && editTextHotelAdress.getText().toString().equals(hotelTable.getAdress1())) {
                        Toast.makeText(context, "No field changed", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        HotelTable hotelTableTable = HotelTable.findById(HotelTable.class, hotelTable.getId());
                        hotelTableTable.name = editTextHotelName.getText().toString();
                        hotelTableTable.adress1 = editTextHotelAdress.getText().toString();
                        hotelTableTable.save();
                        editTextHotelName.setText(null);
                        editTextHotelAdress.setText(null);
                        optionDialog.dismiss();
                        Toast.makeText(context, "Hotel was updated information", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    HotelTable hotelTableTable = HotelTable.findById(HotelTable.class, hotelTable.getId());
                    hotelTableTable.delete();
                    editTextHotelName.setText(null);
                    editTextHotelAdress.setText(null);
                    optionDialog.dismiss();
                    Toast.makeText(context, "Hotel was deleted", Toast.LENGTH_SHORT).show();
                }
            });
            buttonDelete.setOnClickListener(v -> {
                HotelTable hotelTableTable = HotelTable.findById(HotelTable.class, hotelTable.getId());
                hotelTableTable.delete();
                editTextHotelName.setText(null);
                editTextHotelAdress.setText(null);
                optionDialog.dismiss();
                Toast.makeText(context, "Hotel was deleted", Toast.LENGTH_SHORT).show();
            });
            buttonCancel.setOnClickListener(v -> optionDialog.dismiss());
        }
        optionDialog.setView(dialoglayout);
        optionDialog.show();
    }
}
