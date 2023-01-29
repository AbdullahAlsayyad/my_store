package com.abdullah_alsayyad.my_store.data.repository;

import android.content.Context;
import android.database.Cursor;

import com.abdullah_alsayyad.my_store.data.Database;
import com.abdullah_alsayyad.my_store.data.DatabaseStructure;
import com.abdullah_alsayyad.my_store.data.model.Shipment;

import java.util.ArrayList;

public class ShipmentsRepo {
    private final Database db;
    private final ArrayList<Shipment> shipments;

    public ShipmentsRepo(Context context) {
        this.db = new Database(context, Shipment.class);
        this.shipments = new ArrayList<>();
        getData();
    }

    public ArrayList<Shipment> getShipments() {
        return shipments;
    }

    public long newShipment(Shipment shipment) {
        if (shipment == null)
            throw new NullPointerException();
        long result = this.db.newRow(shipment);
        if (result != -1) this.shipments.add(new Shipment( (int) result, shipment.maximum, shipment.minimum, shipment.status, shipment.note, shipment.totalAdded));
        return result;
    }

    public ArrayList<String> activeShipments() {
        ArrayList<String> activeShipments = new ArrayList<>();
        String sql = "SELECT "+ DatabaseStructure.ShipmentTable.SHIPMENT_ID + " FROM " + DatabaseStructure.ShipmentTable.TABLE_NAME
                + " WHERE " + DatabaseStructure.ShipmentTable.STATUS + " = 0";
        Cursor data = Database.getDataBySQL(this.db, sql);
        data.moveToFirst();
        while (!data.isAfterLast()) {
            activeShipments.add(String.valueOf(data.getInt(0)));
            data.moveToNext();
        }
        return activeShipments;
    }

    public Shipment getShipment(int shipmentId){
        Cursor data = this.db.getModel(shipmentId);
        data.moveToFirst();
        if (data.getCount() == 0) return null;
        return DatabaseStructure.ShipmentTable.toShipment(data);
    }
    private void getData() {
        Cursor data = this.db.getData();
        data.moveToFirst();
        while (!data.isAfterLast()) {
            this.shipments.add(DatabaseStructure.ShipmentTable.toShipment(data));
            data.moveToNext();
        }
    }
}
