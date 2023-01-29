package com.abdullah_alsayyad.my_store.data.model;

import android.content.ContentValues;

import com.abdullah_alsayyad.my_store.data.DatabaseStructure;

import java.util.Objects;

public class Shipment implements Model {
    public final Integer shipmentId;
    public final Double maximum;
    public final Double minimum;
    public final Boolean status;
    public final String note;
    public final Double totalAdded;

    public Shipment(int shipmentId, double maximum, double minimum, boolean status, String note, double totalAdded) {
        this.shipmentId = shipmentId;
        this.maximum = maximum;
        this.minimum = minimum;
        this.status = status;
        this.note = note;
        this.totalAdded = totalAdded;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "shipmentId=" + shipmentId +
                ", maximum=" + maximum +
                ", minimum=" + minimum +
                ", status=" + status +
                ", note='" + note + '\'' +
                ", totalAdded=" + totalAdded +
                '}';
    }

    @Override
    public String getTableName() {
        return DatabaseStructure.ShipmentTable.TABLE_NAME;
    }

    @Override
    public ContentValues getValues() {
        return  DatabaseStructure.ShipmentTable.toContentValues(this);
    }

    @Override
    public int getId() {
        return shipmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shipment shipment = (Shipment) o;
        return Objects.equals(shipmentId, shipment.shipmentId);
    }
}
