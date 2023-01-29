package com.abdullah_alsayyad.my_store.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.abdullah_alsayyad.my_store.data.model.Customer;
import com.abdullah_alsayyad.my_store.data.model.Model;
import com.abdullah_alsayyad.my_store.data.model.Order;
import com.abdullah_alsayyad.my_store.data.model.OrderLine;
import com.abdullah_alsayyad.my_store.ui.MainActivity;
import com.abdullah_alsayyad.my_store.data.model.Shipment;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final String TAG = MainActivity.MAIN_TAG+"DB";
    private final String TABLE_NAME;
    private static final String DATABASE_NAME = "my_store.db";
    private static final int VERSION = 1;

    public Database(@Nullable Context context, Class eClass) {
        super(context, DATABASE_NAME, null, VERSION);
        if (eClass == Customer.class) TABLE_NAME = DatabaseStructure.CustomerTable.TABLE_NAME;
        else if (eClass == Order.class) TABLE_NAME = DatabaseStructure.OrderTable.TABLE_NAME;
        else if (eClass == OrderLine.class) TABLE_NAME = DatabaseStructure.OrderLineTable.TABLE_NAME;
        else if (eClass == Shipment.class) TABLE_NAME = DatabaseStructure.ShipmentTable.TABLE_NAME;
        else throw new ClassCastException("The class is not instants of Model");
    }

    boolean x = true;
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseStructure.CustomerTable.createTable());
        db.execSQL(DatabaseStructure.ShipmentTable.createTable());
        db.execSQL(DatabaseStructure.OrderLineTable.createTable());
        db.execSQL(DatabaseStructure.OrderTable.createTable());
        Log.d(TAG, "onCreate: Tables created ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long newRow(Model model){
        return this.getWritableDatabase().insert(model.getTableName(), null, model.getValues());
    }

    public int deleteRow(int id) {
        return this.getWritableDatabase().delete(this.TABLE_NAME, "id = ?", new String[] {String.valueOf(id)});
    }

    public static int deleteRow(SQLiteOpenHelper db, String tableName, String whereClause) {
        return db.getWritableDatabase().delete(tableName, whereClause, null);
    }


    
    public Cursor getData(){
        return this.getReadableDatabase().rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

    public Cursor getData(String condition){
        return this.getReadableDatabase().rawQuery("SELECT * FROM "+TABLE_NAME+" "+condition, null);
    }

    public Cursor getModel(int modelId){
        return this.getReadableDatabase().rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE id = "+modelId+" LIMIT 1", null);
    }

    public static Cursor getDataBySQL(SQLiteOpenHelper db, String sql){
        return db.getReadableDatabase().rawQuery(sql, null);
    }


}