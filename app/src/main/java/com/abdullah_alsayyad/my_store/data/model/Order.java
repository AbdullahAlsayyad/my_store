package com.abdullah_alsayyad.my_store.data.model;

import android.content.ContentValues;

import com.abdullah_alsayyad.my_store.data.DatabaseStructure;

import java.util.Objects;

public class Order implements Model {
    public final Integer orderId;
    public final Integer customerId;
    public final Integer shipmentId;
    public final Double total;
    public final Double paid;
    public final Boolean status;
    public final String note;

    public Order(int orderId, int customerId, int shipmentId, double total, double paid, boolean status, String note) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.shipmentId = shipmentId;
        this.total = total;
        this.paid = paid;
        this.status = status;
        this.note = note;
    }

    @Override
    public String getTableName() {
        return DatabaseStructure.OrderTable.TABLE_NAME;
    }

    @Override
    public ContentValues getValues() {
        return DatabaseStructure.OrderTable.toContentValues(this);
    }

    @Override
    public int getId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }
}
