package com.example.phutlq.sqlitedatabasehelper;

import com.google.common.base.Strings;
import com.orm.SugarRecord;

/**
 * Created by PhuTLQ on 2/16/2017.
 */

public class HotelTable extends SugarRecord {

    String adress1;

    String name;

    public HotelTable() {
    }

    public HotelTable(String name, String adress1) {
        this.adress1 = adress1;
        this.name = name;
    }

    public String getAdress1() {
        return adress1;
    }

    public void setAdress1(String adress1) {
        this.adress1 = adress1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
