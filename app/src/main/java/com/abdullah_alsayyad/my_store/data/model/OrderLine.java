package com.abdullah_alsayyad.my_store.data.model;

import android.content.ContentValues;

import com.abdullah_alsayyad.my_store.data.DatabaseStructure;

import java.util.Objects;

public class OrderLine implements Model {
    public final Integer orderLineId;
    public Integer orderId;
    public final String productName;
    public final String productNumber;
    public final Double unitPrice;
    public final Integer units;
    public final Boolean status;
    public final String note;

    public OrderLine(int orderLineId, int orderId, String productName, String productNumber, double unitPrice, int units, boolean status, String note) {
        this.orderLineId = orderLineId;
        this.orderId = orderId;
        this.productName = productName;
        this.productNumber = productNumber;
        this.unitPrice = unitPrice;
        this.units = units;
        this.status = status;
        this.note = note;
    }

    @Override
    public String getTableName() {
        return DatabaseStructure.OrderLineTable.TABLE_NAME;
    }

    @Override
    public ContentValues getValues() {
        return  DatabaseStructure.OrderLineTable.toContentValues(this);
    }

    @Override
    public int getId() {
        return orderLineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return Objects.equals(orderLineId, orderLine.orderLineId);
    }
}
