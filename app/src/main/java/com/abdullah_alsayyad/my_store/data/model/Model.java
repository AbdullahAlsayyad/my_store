package com.abdullah_alsayyad.my_store.data.model;

import android.content.ContentValues;

public interface Model {
    String getTableName();

    ContentValues getValues();
    int getId();
}
