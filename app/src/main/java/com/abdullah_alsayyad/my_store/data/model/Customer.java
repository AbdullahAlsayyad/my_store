package com.abdullah_alsayyad.my_store.data.model;

import android.content.ContentValues;

import com.abdullah_alsayyad.my_store.data.DatabaseStructure;

import java.util.Objects;

public class Customer implements Model {
    public final Integer customerId;
    public final String name;
    public final String phoneNumber;
    public final String note;

    public Customer(int customerId, String name, String phoneNumber, String note) {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.note = note;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    @Override
    public String getTableName() {
        return DatabaseStructure.CustomerTable.TABLE_NAME;
    }

    @Override
    public ContentValues getValues() {
        return DatabaseStructure.CustomerTable.toContentValues(this);
    }

    @Override
    public int getId() {
        return customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId);
    }

}
